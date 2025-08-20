package com.sisterag.FreelanceWork.data.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sisterag.FreelanceWork.data.dabase.entities.PagoEntity
import com.sisterag.FreelanceWork.databinding.ItemPagoBinding

class PagoAdapter(private val pagoList: List<PagoEntity>)
    : RecyclerView.Adapter<PagoAdapter.ViewHolder>() {

    private var myList = emptyList<PagoEntity>()

    class ViewHolder(val binding: ItemPagoBinding)
        :RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int
    ): PagoAdapter.ViewHolder {

        return ViewHolder(
            ItemPagoBinding.inflate(
                LayoutInflater
                .from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: PagoAdapter.ViewHolder, position: Int) {

        val curItem = pagoList[position]
        if(curItem.pagoid!!.toInt() > 0 && curItem.pagoid!! > 0) {
            holder.binding.apply {
                tvwPagoid.text = "${curItem.pagoid}"
                tvwTrabid.text = "${curItem.trabid}"
                tvwMonto.text = "${curItem.monto}"
                tvwFechapago.text = "${curItem.fechapago}"
                tvwMetodopago.text = "${curItem.metodopago}"
                tvwEstado.text = "${curItem.estado}"
            }
        }
    }

    override fun getItemCount(): Int {
        return pagoList.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun listaPago(newList: List<PagoEntity>) {
        myList = newList
        notifyDataSetChanged()

    }
}