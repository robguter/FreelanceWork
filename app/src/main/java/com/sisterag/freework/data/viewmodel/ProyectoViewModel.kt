package com.sisterag.FreelanceWork.data.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.application
import androidx.lifecycle.viewModelScope
import com.sisterag.FreelanceWork.data.dabase.ApiDatabase
import com.sisterag.FreelanceWork.data.dabase.entities.ProyectoEntity
import com.sisterag.FreelanceWork.data.repository.ProyectoRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ProyectoViewModel(Application: Application)
    : AndroidViewModel(Application) {

    private val repository: ProyectoRepository

    var ProyEnti = ProyectoEntity()

    init {
        val proyectoDao = ApiDatabase
            .getDatabase(this@ProyectoViewModel.application)
            .proyectoDao()
        repository = ProyectoRepository(proyectoDao)

    }

    fun insertProyecto(proyecto: ProyectoEntity) =
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertProyecto(proyecto)
    }

    fun listaProyectos(): LiveData<List<ProyectoEntity>> {
        return repository.getAllProyectos()
    }


}