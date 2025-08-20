package com.sisterag.FreelanceWork.data.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.sisterag.FreelanceWork.data.dabase.entities.UsuarioEntity
import com.sisterag.FreelanceWork.databinding.ItemUsrBinding
import com.sisterag.FreelanceWork.utiles.EndPoint.IMG_URL

class UsuarioAdapter(private val userList: List<UsuarioEntity>)
    : RecyclerView.Adapter<UsuarioAdapter.ViewHolder>() {

    private var myList = emptyList<UsuarioEntity>()
    private lateinit var context: Context

    class ViewHolder(val binding: ItemUsrBinding)
        : RecyclerView.ViewHolder(binding.root){

    }
    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int
    ): ViewHolder {
        context = parent.context
        return ViewHolder(
            ItemUsrBinding.inflate(LayoutInflater
                .from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(
        holder: ViewHolder, position: Int
    ) {
        val curItem = userList[position]
        if(curItem.userid!!.toInt() > 0 && curItem.userid!! > 0) {
            holder.binding.apply {
                tvwUserId.text = "${curItem.userid}"
                tvwNombre.text = curItem.nombre
                tvwApellido.text = curItem.apellido
                tvwEmail.text = curItem.email
                tvwTipouser.text = curItem.tipouser
                tvwInformacion.text = curItem.informacion
                tvwEstatus.text = curItem.estatus
                val url = curItem.imagen

                Glide.with(context)
                    //.load(url)
                    //.into(this)
                    //.into(imgImag)

                    .load(curItem.imagen)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .centerCrop()
                    .into(imgImag)
            }
        }
    }

    override fun getItemCount(): Int {
        return userList.size
    }
    @SuppressLint("NotifyDataSetChanged")
    fun listaUsr(newList: List<UsuarioEntity>) {
        myList = newList
        notifyDataSetChanged()
    }
}