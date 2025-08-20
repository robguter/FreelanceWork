package com.sisterag.FreelanceWork.ui

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.sisterag.FreelanceWork.data.adapter.CalificaAdapter
import com.sisterag.FreelanceWork.data.dabase.entities.CalificaEntity
import com.sisterag.FreelanceWork.data.viewmodel.CalificaViewModel
import com.sisterag.FreelanceWork.databinding.FragmentCalificaBinding
import com.sisterag.FreelanceWork.utiles.EndPoint
import com.sisterag.FreelanceWork.utiles.EndPoint.limpiarCampos
import kotlinx.coroutines.launch

class CalificaFragment : Fragment() {

    private var _binding: FragmentCalificaBinding? = null
    private lateinit var context: Context
    private lateinit var rvAdapter: CalificaAdapter
    val binding get() = _binding!!
    private lateinit var caliList: List<CalificaEntity>

    private lateinit var lLayout: ConstraintLayout

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?, savedInstanceState: Bundle?
    ): View {

        _binding = FragmentCalificaBinding.inflate(inflater, container,
            false)
        val root: View = binding.root
        context = requireActivity().applicationContext
        lLayout = binding.linearLayout

        val caliVM =
            ViewModelProvider(this)[CalificaViewModel::class.java]

        caliList = listOf()
        cargaCalifica()

        val btnGuardar = binding.btnGuardar
        btnGuardar.setOnClickListener(View.OnClickListener {
            val bValor: Boolean = EndPoint.comprueba(binding.linearLayout, context)
            if(!bValor) {
                return@OnClickListener
            }
            binding.pbProgress.visibility = View.VISIBLE
            agregaCalifica(caliVM)
        })
        return root
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun cargaCalifica() {
        val caliVM = ViewModelProvider(this)[CalificaViewModel::class.java]
        caliVM.ListaCalifica().observe(viewLifecycleOwner) {
            caliList = it
            val rvCali = binding.rvwRecyclr
            rvCali.apply {
                rvAdapter = CalificaAdapter(caliList)
                rvCali.setLayoutManager(LinearLayoutManager(activity))
                rvCali.setHasFixedSize(true)
                adapter = rvAdapter
                rvCali.setAdapter(adapter)
            }
            rvAdapter.notifyDataSetChanged()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun agregaCalifica(caliVM: CalificaViewModel) {
        lifecycleScope.launch {
            val sTrabid = binding.etTrabid.text.toString()
            val sCalificadorid = binding.etCalificadorid.text.toString()
            val sCalificadoid = binding.etCalificadoid.text.toString()
            val sPuntuacion = binding.etPuntuacion.text.toString()
            val sComentario = binding.etComentario.text.toString()
            val sFechacali = binding.etFechacali.text.toString()

            val calEnt = caliVM.CaliEnti
            calEnt.trabid = sTrabid.toInt()
            calEnt.calificadorid = sCalificadorid.toInt()
            calEnt.calificadoid = sCalificadoid.toInt()
            calEnt.puntuacion = sPuntuacion.toInt()
            calEnt.comentario = sComentario
            calEnt.fechacali = sFechacali

            caliVM.insertCalifica(calEnt)
            limpiarCampos(lLayout,context)
            binding.pbProgress.visibility = View.GONE
            cargaCalifica()
        }

    }

}