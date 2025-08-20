package com.sisterag.FreelanceWork.data.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.application
import androidx.lifecycle.viewModelScope
import com.sisterag.FreelanceWork.data.dabase.ApiDatabase
import com.sisterag.FreelanceWork.data.dabase.entities.TrabajoEntity
import com.sisterag.FreelanceWork.data.repository.TrabajoRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TrabajoViewModel(Application: Application)
    : AndroidViewModel(Application) {

        private val repository: TrabajoRepository
        var TrabEnti = TrabajoEntity()

    init {
        val trabajoDao = ApiDatabase
            .getDatabase(this@TrabajoViewModel.application)
            .trabajoDao()
        repository = TrabajoRepository(trabajoDao)
    }

    fun insertTrabajo(trabajo: TrabajoEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertTrabajo(trabajo)
        }
    }

    fun listaTrabajos(): LiveData<List<TrabajoEntity>> {
        return repository.getAllTrabajos()
    }



}