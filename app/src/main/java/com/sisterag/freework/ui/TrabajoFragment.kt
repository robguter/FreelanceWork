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
import com.sisterag.FreelanceWork.data.adapter.TrabajoAdapter
import com.sisterag.FreelanceWork.data.dabase.entities.TrabajoEntity
import com.sisterag.FreelanceWork.data.viewmodel.TrabajoViewModel
import com.sisterag.FreelanceWork.databinding.FragmentTrabajoBinding
import com.sisterag.FreelanceWork.utiles.EndPoint
import kotlinx.coroutines.launch

class TrabajoFragment : Fragment() {
    private var _binding: FragmentTrabajoBinding? = null
    private lateinit var context: Context
    private lateinit var rvAdapter: TrabajoAdapter
    private val binding get() = _binding!!
    private lateinit var trabaList: List<TrabajoEntity>

    private lateinit var lLayout: ConstraintLayout

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentTrabajoBinding.inflate(inflater, container, false)
        val root: View = binding.root
        context = requireActivity().applicationContext
        lLayout = binding.linearLayout

        val trabaVM =
            ViewModelProvider(this).get(TrabajoViewModel::class.java)

        trabaList = listOf()
        cargaTrabajo()

        val btnGuardar = binding.btnGuardar
        btnGuardar.setOnClickListener(View.OnClickListener {
            val bValor: Boolean = EndPoint.comprueba(binding.linearLayout, context)
            if (!bValor) {
                return@OnClickListener
            }
            binding.pbProgress.visibility = View.VISIBLE
            agregaTrabajo(trabaVM)

        })

        return root
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun cargaTrabajo() {

        val trabaViewModel = ViewModelProvider(this)[TrabajoViewModel::class.java]
        trabaViewModel.listaTrabajos().observe(viewLifecycleOwner) {
            trabaList = it
            val rvTraba = binding.rvwRecyclr
            rvTraba.apply {
                rvAdapter = TrabajoAdapter(trabaList)
                rvTraba.setLayoutManager(LinearLayoutManager(activity))
                rvTraba.setHasFixedSize(true)
                adapter = rvAdapter
                rvTraba.setAdapter(adapter)
            }
            rvAdapter.notifyDataSetChanged()
        }
    }

    private fun agregaTrabajo(trabaVM: TrabajoViewModel) {

        lifecycleScope.launch {
            val sProyid = binding.etProyid.text.toString()
            val sFreeid = binding.etFreeid.text.toString()
            val sDescripcion = binding.etDescripcion.text.toString()
            val sPresupuesto = binding.etPresupuesto.text.toString()
            val sFechainicio = binding.etFechainicio.text.toString()
            val sFechafin = binding.etFechafin.text.toString()
            val sEstado = binding.etEstado.text.toString()

            val trabaEnt = trabaVM.TrabEnti
            trabaEnt.proyid = sProyid.toInt()
            trabaEnt.freeid = sFreeid.toInt()
            trabaEnt.descripcion = sDescripcion
            trabaEnt.presupuesto = sPresupuesto.toInt()
            trabaEnt.fechainicio = sFechainicio
            trabaEnt.fechafin = sFechafin
            trabaEnt.estado = sEstado

            trabaVM.insertTrabajo(trabaEnt)
            EndPoint.limpiarCampos(lLayout, context)
            binding.pbProgress.visibility = View.GONE
            cargaTrabajo()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}