package com.sisterag.FreelanceWork.ui

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sisterag.FreelanceWork.clients.RetrofitClient
import com.sisterag.FreelanceWork.data.adapter.UsuarioAdapter
import com.sisterag.FreelanceWork.data.dabase.entities.UsuarioEntity
import com.sisterag.FreelanceWork.data.viewmodel.UsuarioViewModel
import com.sisterag.FreelanceWork.databinding.FragmentUsuarioBinding
import com.sisterag.FreelanceWork.servicios.ApiServiceUsr
import com.sisterag.FreelanceWork.utiles.EndPoint
import com.sisterag.FreelanceWork.utiles.EndPoint.limpiarCampos
import com.sisterag.FreelanceWork.utiles.getPathFromUri
import com.sisterag.FreelanceWork.utiles.getRealPathFromURI
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException

class UsuarioFragment : Fragment() {

    private val apiService = RetrofitClient.create(ApiServiceUsr::class.java)
    private lateinit var usrList: List<UsuarioEntity>
    private lateinit var rvadapter: UsuarioAdapter

    private var _binding: FragmentUsuarioBinding? = null
    val binding get() = _binding!!

    private lateinit var rvwUsr: RecyclerView
    private lateinit var edtNombre: EditText
    private lateinit var edtApellido: EditText
    private lateinit var edtTelefono: EditText
    private lateinit var edtEmail: EditText
    private lateinit var edtUsuario: EditText
    private lateinit var edtClave: EditText
    private lateinit var edtTipouser: EditText
    private lateinit var edtInformacion: EditText
    private lateinit var edtEstatus: EditText

    private lateinit var sNombre: String
    private lateinit var sApellido: String
    private lateinit var sTelefono: String
    private lateinit var sEmail: String
    private lateinit var sUsuario: String
    private lateinit var sClave: String
    private lateinit var sTipouser: String
    private lateinit var sInformacion: String
    private lateinit var sEstatus: String

    private lateinit var btnGuardar: Button
    private lateinit var ivImage: ImageView

    private lateinit var pbProgress: ProgressBar
    private lateinit var lLayout: ConstraintLayout

    private lateinit var context: Context

    private val contentResolver = activity?.contentResolver
    private lateinit var imagePath: String
    private lateinit var imageUri: Uri

    private lateinit var getContent: ActivityResultLauncher<String> // Launcher para seleccionar contenido
    private lateinit var userVM: UsuarioViewModel
    private lateinit var imagePickerLauncher: ActivityResultLauncher<Intent>

    @OptIn(DelicateCoroutinesApi::class)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentUsuarioBinding.inflate(inflater, container,
            false)
        val root: View = binding.root
        context = requireActivity().applicationContext
        lLayout = binding.linearLayout

        userVM =
            ViewModelProvider(this)[UsuarioViewModel::class]

        context = requireActivity().applicationContext
        pbProgress = binding.pbProgress
        rvwUsr = binding.rvwRecyclr

        edtNombre = binding.etNombre
        edtNombre.setOnClickListener ({
            rvwUsr.apply {
                rvwUsr.setAdapter(null)
            }
        })
        edtApellido = binding.etApellido
        edtApellido.setOnClickListener ({
            rvwUsr.apply {
                rvwUsr.setAdapter(null)
            }
        })
        edtTipouser = binding.etTipouser
        edtTipouser.setOnClickListener ({
            rvwUsr.apply {
                rvwUsr.setAdapter(null)
            }
        })
        edtTelefono = binding.etTelefono
        edtTelefono.setOnClickListener ({
            rvwUsr.apply {
                rvwUsr.setAdapter(null)
            }
        })
        edtEmail = binding.etEmail
        edtEmail.setOnClickListener ({
            rvwUsr.apply {
                rvwUsr.setAdapter(null)
            }
        })
        edtUsuario = binding.etUsuario
        edtUsuario.setOnClickListener ({
            rvwUsr.apply {
                rvwUsr.setAdapter(null)
            }
        })
        edtClave = binding.etClave
        edtClave.setOnClickListener ({
            rvwUsr.apply {
                rvwUsr.setAdapter(null)
            }
        })
        edtInformacion = binding.etInformacion
        edtInformacion.setOnClickListener ({
            rvwUsr.apply {
                rvwUsr.setAdapter(null)
            }
        })
        edtEstatus = binding.etEstatus
        edtEstatus.setOnClickListener ({
            rvwUsr.apply {
                rvwUsr.setAdapter(null)
            }
        })
        ivImage = binding.imgImagen
        ivImage.setOnClickListener ({
            getContent.launch("image/*")
        })

        // Inicializar registerForActivityResult
        imagePickerLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val data: Intent? = result.data
                // Aquí puedes procesar la imagen obtenida
                val imageUri: Uri? = data?.data
                //  O  si es un bitmap, puedes mostrarlo en un ImageView
                //  Ej:  val bitmap: Bitmap? = data?.extras?.get("data") as? Bitmap
                //  imageView.setImageBitmap(bitmap)

            }
        }

        getContent = registerForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
            uri?.let {
                imageUri = it
                ivImage.setImageURI(it)
                imagePath = getRealPathFromURI(context, imageUri).toString()
                binding.tvwImagenUrl.text = imagePath
            }
        }
        btnGuardar = binding.btnGuardar
        btnGuardar.setOnClickListener(View.OnClickListener {
            val bValor: Boolean = EndPoint.comprueba(lLayout, this.context)
            if(!bValor) {
                return@OnClickListener
            }

            binding.pbProgress.visibility = View.VISIBLE
            agregaUsuario()

        })
        pbProgress = binding.pbProgress
        lLayout = binding.linearLayout

        usrList = listOf()

        binding.pbProgress.visibility = View.VISIBLE
        cargaUsuarios()
        return root
    }

    private fun agregaUsuario() {
        lifecycleScope.launch {
            binding.pbProgress.visibility = View.VISIBLE
            val sNombr = edtNombre.text.toString()
            val sApell = edtApellido.text.toString()
            val sTipou = edtTipouser.text.toString()
            val sTelef = edtTelefono.text.toString()
            val sEmail = edtEmail.text.toString()
            val sUsuar = edtUsuario.text.toString()
            //val sClave = edtClave.text.toString()
            val sInfor = edtInformacion.text.toString()
            val sEstat = edtEstatus.text.toString()
            val sImagen = binding.tvwImagenUrl.text.toString()

            val userEnt = userVM.userEnti
            userEnt.nombre = sNombr
            userEnt.apellido = sApell
            userEnt.tipouser = sTipou
            userEnt.telefono = sTelef
            userEnt.email = sEmail
            userEnt.usuario = sUsuar
            userEnt.informacion = sInfor
            userEnt.estatus = sEstat
            userEnt.imagen = sImagen

            userVM.insertUsuario(userEnt)
            limpiarCampos(lLayout,context)
            binding.pbProgress.visibility = View.GONE
            cargaUsuarios()
        }
    }
    @SuppressLint("NotifyDataSetChanged")
    private fun cargaUsuarios() {
        //val userVM = ViewModelProvider(this)[UsuarioViewModel::class]
        userVM.listaUsuarios().observe(viewLifecycleOwner) {
            usrList = it
            val rvUser = binding.rvwRecyclr
            rvUser.apply {
                rvadapter = UsuarioAdapter(usrList)
                rvUser.setLayoutManager(LinearLayoutManager(activity))
                rvUser.setHasFixedSize(true)
                adapter = rvadapter
                rvUser.setAdapter(adapter)
                rvadapter.notifyDataSetChanged()
            }
        }
        binding.pbProgress.visibility = View.GONE
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
