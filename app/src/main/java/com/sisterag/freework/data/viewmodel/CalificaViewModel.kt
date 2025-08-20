package com.sisterag.FreelanceWork.data.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData

import androidx.lifecycle.application
import androidx.lifecycle.viewModelScope
import com.sisterag.FreelanceWork.data.dabase.ApiDatabase
import com.sisterag.FreelanceWork.data.dabase.entities.CalificaEntity
import com.sisterag.FreelanceWork.data.repository.CalificaRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class CalificaViewModel(Application: Application) :
    AndroidViewModel(Application) {

    private val repository: CalificaRepository
    var CaliEnti = CalificaEntity()

    init {
        val calificaDao = ApiDatabase
            .getDatabase(this@CalificaViewModel.application)
            .calificaDao()
        repository = CalificaRepository(calificaDao)
    }

    fun ListaCalifica(): LiveData<List<CalificaEntity>> {
        return repository.getAllCalifica()
    }

    fun insertCalifica(califica: CalificaEntity) =
        viewModelScope.launch(Dispatchers.IO) {
        repository.insertCalifica(califica)
    }

}

