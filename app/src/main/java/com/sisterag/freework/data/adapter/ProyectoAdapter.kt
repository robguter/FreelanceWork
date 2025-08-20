package com.sisterag.FreelanceWork.data.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sisterag.FreelanceWork.data.dabase.entities.ProyectoEntity
import com.sisterag.FreelanceWork.databinding.ItemProyBinding

class ProyectoAdapter(private val proyList: List<ProyectoEntity>)
    : RecyclerView.Adapter<ProyectoAdapter.ViewHolder>() {

    private var myList = emptyList<ProyectoEntity>()

    class ViewHolder(val binding: ItemProyBinding)
        :RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int
    ): ProyectoAdapter.ViewHolder {

        return ViewHolder(
            ItemProyBinding.inflate(
                LayoutInflater
                .from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: ProyectoAdapter.ViewHolder, position: Int) {

        val curItem = proyList[position]
        if(curItem.proyid!!.toInt() > 0 && curItem.proyid!! > 0) {
            holder.binding.apply {
                tvwCnteid.text = "${curItem.cnteid}"
                tvwTitulo.text = "${curItem.titulo}"
                tvwDescripcion.text = "${curItem.descripcion}"
                tvwPresupuesto.text = "${curItem.presupuesto}"
                tvwFechacreacion.text = "${curItem.fechacreacion}"
                tvwFechalimite.text = "${curItem.fechalimite}"
                tvwEstado.text = "${curItem.estado}"
            }
        }
    }

    override fun getItemCount(): Int {
        return proyList.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun listaProy(newList: List<ProyectoEntity>) {
        myList = newList
        notifyDataSetChanged()
    }
}