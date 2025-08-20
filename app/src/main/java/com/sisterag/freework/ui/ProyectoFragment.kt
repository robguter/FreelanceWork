package com.sisterag.FreelanceWork.ui

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.sisterag.FreelanceWork.data.adapter.ProyectoAdapter
import com.sisterag.FreelanceWork.data.dabase.entities.ProyectoEntity
import com.sisterag.FreelanceWork.data.viewmodel.ProyectoViewModel
import com.sisterag.FreelanceWork.databinding.FragmentProyectoBinding
import com.sisterag.FreelanceWork.utiles.EndPoint
import kotlinx.coroutines.launch

class ProyectoFragment : Fragment() {

    private var _binding: FragmentProyectoBinding? = null
    private lateinit var context: Context
    private lateinit var rvAdapter: ProyectoAdapter
    private val binding get() = _binding!!
    private lateinit var proyList: List<ProyectoEntity>

    private lateinit var lLayout: ConstraintLayout

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentProyectoBinding.inflate(inflater, container, false)
        val root: View = binding.root
        context = requireActivity().applicationContext
        lLayout = binding.linearLayout

        val proyVM =
            ViewModelProvider(this).get(ProyectoViewModel::class.java)

        proyList = listOf()
        cargaProyecto()

        val btnGuardar = binding.btnGuardar
        btnGuardar.setOnClickListener(View.OnClickListener {
            val bValor: Boolean = EndPoint.comprueba(binding.linearLayout, context)
            if (!bValor) {
                return@OnClickListener
            }
            binding.pbProgress.visibility = View.VISIBLE
            agregaProyecto(proyVM)
        })

        return root
    }

    fun agregaProyecto(proyVM: ProyectoViewModel) {
        lifecycleScope.launch {
            val sCnteid = binding.etCnteid.text.toString()
            val sTitulo = binding.etTitulo.text.toString()
            val sDescripcion = binding.etDescripcion.text.toString()
            val sPresupuesto = binding.etPresupuesto.text.toString()
            val sFechacreacion = binding.etFechacreacion.text.toString()
            val sFechalimite = binding.etFechalimite.text.toString()
            val sEstado = binding.etEstado.text.toString()

            val proyEnt = proyVM.ProyEnti
            proyEnt.cnteid = sCnteid.toInt()
            proyEnt.titulo = sTitulo
            proyEnt.descripcion = sDescripcion
            proyEnt.presupuesto = sPresupuesto.toInt()
            proyEnt.fechacreacion = sFechacreacion
            proyEnt.fechalimite = sFechalimite
            proyEnt.estado = sEstado

            proyVM.insertProyecto(proyEnt)
            EndPoint.limpiarCampos(lLayout, context)
            binding.pbProgress.visibility = View.GONE
            cargaProyecto()

        }
    }
    @SuppressLint("NotifyDataSetChanged")
    private fun cargaProyecto() {

        val proyViewModel = ViewModelProvider(this)[ProyectoViewModel::class.java]
        proyViewModel.listaProyectos().observe(viewLifecycleOwner) {
            proyList = it

            val rvProy = binding.rvwRecyclr
            rvProy.apply {
                rvAdapter = ProyectoAdapter(proyList)
                rvProy.setLayoutManager(LinearLayoutManager(activity))
                rvProy.setHasFixedSize(true)
                adapter = rvAdapter
                rvProy.setAdapter(adapter)
            }
            rvAdapter.notifyDataSetChanged()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}