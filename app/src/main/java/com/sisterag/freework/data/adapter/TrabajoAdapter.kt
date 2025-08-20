package com.sisterag.FreelanceWork.data.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sisterag.FreelanceWork.data.dabase.entities.TrabajoEntity
import com.sisterag.FreelanceWork.databinding.ItemTrabaBinding

class TrabajoAdapter(private val trabajoList: List<TrabajoEntity>)
    : RecyclerView.Adapter<TrabajoAdapter.ViewHolder>() {

    private var myList = emptyList<TrabajoEntity>()

    class ViewHolder(val binding: ItemTrabaBinding)
        :RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TrabajoAdapter.ViewHolder {

        return ViewHolder(
            ItemTrabaBinding.inflate(
                LayoutInflater
                .from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: TrabajoAdapter.ViewHolder, position: Int) {

        val curItem = trabajoList[position]
        if(curItem.trabid!!.toInt() > 0 && curItem.trabid!! > 0) {
            holder.binding.apply {
                tvwTrabid.text = "${curItem.trabid}"
                tvwProyid.text = "${curItem.proyid}"
                tvwFreeid.text = "${curItem.freeid}"
                tvwDescripcion.text = "${curItem.descripcion}"
                tvwPresupuesto.text = "${curItem.presupuesto}"
                tvwFechainicio.text = "${curItem.fechainicio}"
                tvwFechafin.text = "${curItem.fechafin}"
                tvwEstado.text = "${curItem.estado}"
            }
        }
    }

    override fun getItemCount(): Int {
        return trabajoList.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun listaTraba(newList: List<TrabajoEntity>) {
        myList = newList
        notifyDataSetChanged()

    }
}