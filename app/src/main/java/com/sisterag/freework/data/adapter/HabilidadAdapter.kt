package com.sisterag.FreelanceWork.data.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sisterag.FreelanceWork.data.dabase.entities.CalificaEntity
import com.sisterag.FreelanceWork.data.dabase.entities.HabilidadEntity
import com.sisterag.FreelanceWork.databinding.ItemHabilBinding

class HabilidadAdapter(private val habList: List<HabilidadEntity>)
    : RecyclerView.Adapter<HabilidadAdapter.ViewHolder>() {

    private var myList = emptyList<HabilidadEntity>()


    class ViewHolder(val binding: ItemHabilBinding)
        : RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        return ViewHolder(
            ItemHabilBinding.inflate(
                LayoutInflater
                .from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int
    ) {
        val curItem = habList[position]
        if(curItem.hablid!!.toInt() > 0 && curItem.hablid!! > 0) {
            holder.binding.apply {
                tvwHablid.text = "${curItem.hablid}"
                tvwNombre.text = curItem.nombre
            }
        }
    }

    override fun getItemCount(): Int {
        return habList.size
    }
    @SuppressLint("NotifyDataSetChanged")
    fun listaCali(newList: List<HabilidadEntity>) {
        myList = newList
        notifyDataSetChanged()
    }

}