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
import com.sisterag.FreelanceWork.data.adapter.PagoAdapter
import com.sisterag.FreelanceWork.data.dabase.entities.PagoEntity
import com.sisterag.FreelanceWork.data.viewmodel.PagoViewModel
import com.sisterag.FreelanceWork.databinding.FragmentPagoBinding
import com.sisterag.FreelanceWork.utiles.EndPoint
import kotlinx.coroutines.launch

class PagoFragment : Fragment() {

    private var _binding: FragmentPagoBinding? = null
    private lateinit var context: Context
    private lateinit var rvAdapter: PagoAdapter
    private val binding get() = _binding!!
    private lateinit var pagoList: List<PagoEntity>

    private lateinit var lLayout: ConstraintLayout

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentPagoBinding.inflate(inflater, container, false)
        val root: View = binding.root
        context = requireActivity().applicationContext
        lLayout = binding.linearLayout

        val pagoVM =
            ViewModelProvider(this).get(PagoViewModel::class.java)

        pagoList = listOf()
        cargaPago()

        val btnGuardar = binding.btnGuardar
        btnGuardar.setOnClickListener(View.OnClickListener {
            val bValor: Boolean = EndPoint.comprueba(binding.linearLayout, context)
            if (!bValor) {
                return@OnClickListener
            }
            binding.pbProgress.visibility = View.VISIBLE
            agregaPago(pagoVM)
        })

        return root
    }

    private fun agregaPago(pagoVM: PagoViewModel) {
        lifecycleScope.launch {
            val sTrabid = binding.etTrabid.text.toString()
            val sMonto = binding.etMonto.text.toString()
            val sFechapago = binding.etFechapago.text.toString()
            val sMetodopago = binding.etMetodopago.text.toString()
            val sEstado = binding.etEstado.text.toString()

            val pagoEnt = pagoVM.PagoEnti
            pagoEnt.trabid = sTrabid.toInt()
            pagoEnt.monto = sMonto.toInt()
            pagoEnt.fechapago = sFechapago
            pagoEnt.metodopago = sMetodopago
            pagoEnt.estado = sEstado

            pagoVM.insertPago(pagoEnt)
            EndPoint.limpiarCampos(lLayout, context)
            binding.pbProgress.visibility = View.GONE
            cargaPago()
        }
    }
    @SuppressLint("NotifyDataSetChanged")
    private fun cargaPago() {

        val pagoViewModel = ViewModelProvider(this)[PagoViewModel::class.java]
        pagoViewModel.ListaPago().observe(viewLifecycleOwner) {
            pagoList = it
            val rvPago = binding.rvwRecyclr
            rvPago.apply {
                rvAdapter = PagoAdapter(pagoList)
                rvPago.setLayoutManager(LinearLayoutManager(activity))
                rvPago.setHasFixedSize(true)
                adapter = rvAdapter
                rvPago.setAdapter(adapter)
            }
            rvAdapter.notifyDataSetChanged()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}