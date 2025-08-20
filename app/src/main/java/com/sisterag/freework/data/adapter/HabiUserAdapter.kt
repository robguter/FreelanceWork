package com.sisterag.FreelanceWork.data.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sisterag.FreelanceWork.data.dabase.entities.HabiluserEntity
import com.sisterag.FreelanceWork.databinding.ItemHabilUserBinding

class HabiUserAdapter(private val habusrList: List<HabiluserEntity>)
    : RecyclerView.Adapter<HabiUserAdapter.ViewHolder>() {


    private var myList = emptyList<HabiluserEntity>()

    class ViewHolder(val binding: ItemHabilUserBinding)
        :RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): HabiUserAdapter.ViewHolder {
        return ViewHolder(
            ItemHabilUserBinding.inflate(
                LayoutInflater
                .from(parent.context), parent,
                false)
        )
    }

    override fun onBindViewHolder(holder: HabiUserAdapter.ViewHolder, position: Int) {
        val curItem = habusrList[position]
        if(curItem.id!!.toInt() > 0 && curItem.id!! > 0) {
            holder.binding.apply {
                tvwId.text = "${curItem.id}"
                tvwFreeid.text = "${curItem.freeid}"
                tvwHablid.text = "${curItem.hablid}"
                tvwNivel.text = curItem.nivel
            }
        }
    }

    override fun getItemCount(): Int {
        return habusrList.size
    }
    @SuppressLint("NotifyDataSetChanged")
    fun listaHabilUser(newList: List<HabiluserEntity>) {
        myList = newList
        notifyDataSetChanged()

    }
}