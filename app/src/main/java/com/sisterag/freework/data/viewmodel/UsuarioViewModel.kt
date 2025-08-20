package com.sisterag.FreelanceWork.data.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.application
import androidx.lifecycle.viewModelScope
import com.sisterag.FreelanceWork.data.dabase.ApiDatabase
import com.sisterag.FreelanceWork.data.dabase.entities.UsuarioEntity
import com.sisterag.FreelanceWork.data.repository.UsuarioRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UsuarioViewModel(Application: Application)
    : AndroidViewModel(Application) {

        private val repository: UsuarioRepository
        var userEnti = UsuarioEntity()

    init {
        val usuarioDao = ApiDatabase
            .getDatabase(this@UsuarioViewModel.application)
            .usuarioDao()
        repository = UsuarioRepository(usuarioDao)
    }

    fun listaUsuarios(): LiveData<List<UsuarioEntity>> {
        return repository.getAllUsuarios()
    }

    fun insertUsuario(usuario: UsuarioEntity) =
        viewModelScope.launch(Dispatchers.IO) {
        repository.insertUsuario(usuario)
    }

}