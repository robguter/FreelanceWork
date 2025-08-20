package com.sisterag.FreelanceWork.data.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData

import androidx.lifecycle.application
import androidx.lifecycle.viewModelScope
import com.sisterag.FreelanceWork.data.dabase.ApiDatabase
import com.sisterag.FreelanceWork.data.dabase.entities.HabilidadEntity
import com.sisterag.FreelanceWork.data.repository.HabilitaRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HabilitaViewModel(Application: Application)
    :AndroidViewModel(Application) {

        private val repository: HabilitaRepository
        var HabiEnti = HabilidadEntity()

    init {
        val habilitaDao = ApiDatabase
            .getDatabase(this@HabilitaViewModel.application)
            .habilidadDao()
        repository = HabilitaRepository(habilitaDao)
    }

    fun ListaHabilita(): LiveData<List<HabilidadEntity>> {
        return repository.getAllHabilita()
    }

    fun insertHabilita(habilita: HabilidadEntity) =
        viewModelScope.launch(Dispatchers.IO) {
        repository.insertHabilita(habilita)
    }



}