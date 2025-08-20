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
import com.sisterag.FreelanceWork.data.adapter.HabiUserAdapter
import com.sisterag.FreelanceWork.data.dabase.entities.HabiluserEntity
import com.sisterag.FreelanceWork.data.viewmodel.HabilUserViewModel
import com.sisterag.FreelanceWork.databinding.FragmentHabilUserBinding
import com.sisterag.FreelanceWork.utiles.EndPoint
import kotlinx.coroutines.launch

class HabilUserFragment : Fragment() {

    private var _binding: FragmentHabilUserBinding? = null
    private lateinit var context: Context
    private lateinit var rvAdapter: HabiUserAdapter
    private val binding get() = _binding!!
    private lateinit var habusrList: List<HabiluserEntity>
    private lateinit var habuserVM: HabilUserViewModel

    private lateinit var lLayout: ConstraintLayout

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentHabilUserBinding.inflate(inflater,
            container, false)
        val root: View = binding.root
        context = requireActivity().applicationContext
        lLayout = binding.linearLayout

        habuserVM =
            ViewModelProvider(this).get(HabilUserViewModel::class.java)

        habusrList = listOf()
        cargaHabilUser()

        val btnGuardar = binding.btnGuardar
        btnGuardar.setOnClickListener(View.OnClickListener {
            val bValor: Boolean = EndPoint.comprueba(binding.linearLayout, context)
            if(!bValor) {
                return@OnClickListener
            }
            binding.pbProgress.visibility = View.VISIBLE
            agregaHabilUser(habuserVM)
        })

        return root
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun cargaHabilUser() {
        habuserVM = ViewModelProvider(this)[HabilUserViewModel::class.java]
        habuserVM.ListaHabilUser().observe(viewLifecycleOwner) {

            habusrList = it
            val rvHabilUser = binding.rvwRecyclr
            rvHabilUser.apply {
                rvAdapter = HabiUserAdapter(habusrList)
                rvHabilUser.setLayoutManager(LinearLayoutManager(activity))
                rvHabilUser.setHasFixedSize(true)
                adapter = rvAdapter
                rvHabilUser.setAdapter(adapter)
                rvAdapter.notifyDataSetChanged()
            }
        }
    }

    fun agregaHabilUser(habuserVM: HabilUserViewModel) {
        lifecycleScope.launch {
            val sfreeid = binding.etFreeid.text.toString()
            val shablid = binding.etHablid.text.toString()
            val snivel = binding.etNivel.text.toString()

            val habEnt = habuserVM.habiluserEnti
            habEnt.freeid = sfreeid.toInt()
            habEnt.hablid = shablid.toInt()
            habEnt.nivel = snivel

            habuserVM.insertHabilUser(habEnt)
            EndPoint.limpiarCampos(lLayout, context)
            binding.pbProgress.visibility = View.GONE
            cargaHabilUser()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}