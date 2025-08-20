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
import com.sisterag.FreelanceWork.data.adapter.HabilidadAdapter
import com.sisterag.FreelanceWork.data.dabase.entities.HabilidadEntity
import com.sisterag.FreelanceWork.data.viewmodel.HabilitaViewModel
import com.sisterag.FreelanceWork.databinding.FragmentHabilidadBinding
import com.sisterag.FreelanceWork.utiles.EndPoint
import kotlinx.coroutines.launch

class HabilidadFragment : Fragment() {

    private var _binding: FragmentHabilidadBinding? = null
    private lateinit var context: Context
    private lateinit var rvAdapter: HabilidadAdapter
    val binding get() = _binding!!
    private lateinit var habList: List<HabilidadEntity>

    private lateinit var lLayout: ConstraintLayout

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentHabilidadBinding.inflate(inflater, container,
            false)
        val root: View = binding.root
        context = requireActivity().applicationContext
        lLayout = binding.linearLayout

        val habiVM =
            ViewModelProvider(this)[HabilitaViewModel::class]

        habList = listOf()
        cargaHabilidad()

        val btnGuardar = binding.btnGuardar
        btnGuardar.setOnClickListener(View.OnClickListener {
            val bValor: Boolean = EndPoint.comprueba(binding.linearLayout, context)
            if(!bValor) {
                return@OnClickListener
            }
            binding.pbProgress.visibility = View.VISIBLE
            agregaHabilidad(habiVM)
        })
        return root
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun cargaHabilidad() {
        val habiVM = ViewModelProvider(this)[HabilitaViewModel::class.java]
        habiVM.ListaHabilita().observe(viewLifecycleOwner) {
            habList = it
            val rvHabi = binding.rvwRecyclr
            rvHabi.apply {
                rvAdapter = HabilidadAdapter(habList)
                rvHabi.setLayoutManager(LinearLayoutManager(activity))
                rvHabi.setHasFixedSize(true)
                adapter = rvAdapter
                rvHabi.setAdapter(adapter)
                rvAdapter.notifyDataSetChanged()
            }
        }
    }
    private fun agregaHabilidad(habiVM: HabilitaViewModel) {
        lifecycleScope.launch {
            val sNombre = binding.etNombre.text.toString()
            val habiEnt = habiVM.HabiEnti
            habiEnt.nombre = sNombre

            habiVM.insertHabilita(habiEnt)
            EndPoint.limpiarCampos(lLayout, context)
            binding.pbProgress.visibility = View.GONE
            cargaHabilidad()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}