package com.sisterag.FreelanceWork.data.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sisterag.FreelanceWork.data.dabase.entities.CalificaEntity
import com.sisterag.FreelanceWork.databinding.ItemCaliBinding

class CalificaAdapter(private val caliList: List<CalificaEntity>):
    RecyclerView.Adapter<CalificaAdapter.ViewHolder>() {

    private var myList = emptyList<CalificaEntity>()

    class ViewHolder(val binding: ItemCaliBinding)
        :RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder( parent: ViewGroup, viewType: Int ): ViewHolder {

        return ViewHolder(
            ItemCaliBinding.inflate(LayoutInflater
                .from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder( holder: ViewHolder, position: Int ) {
        val curItem = caliList[position]
        if(curItem.caliid!!.toInt() > 0 && curItem.caliid > 0) {
            holder.binding.apply {
                tvwCaliid.text = "${curItem.caliid}"
                tvwTrabid.text = "${curItem.trabid}"
                tvwCalificadorid.text = "${curItem.calificadorid}"
                tvwCalificadoid.text = "${curItem.calificadoid}"
                tvwPuntuacion.text = "${curItem.puntuacion}"
                tvwComentario.text = curItem.comentario
                tvwFechacali.text = "${curItem.fechacali}"

            }
        }
    }
    @SuppressLint("NotifyDataSetChanged")
    fun listaCali(newList: List<CalificaEntity>) {
        myList = newList
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return caliList.size
    }
}