package com.sisterag.FreelanceWork.data.repository

import androidx.lifecycle.LiveData
import com.sisterag.FreelanceWork.data.dabase.dao.UsuarioDao
import com.sisterag.FreelanceWork.data.dabase.entities.UsuarioEntity

class UsuarioRepository(private val usuarioDao: UsuarioDao) {

    suspend fun insertUsuario(usuario: UsuarioEntity) {
        usuarioDao.insertUsuario(usuario)
    }

    fun getAllUsuarios(): LiveData<List<UsuarioEntity>> {
        return usuarioDao.getAllUsuarios()
    }

}