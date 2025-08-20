package com.sisterag.FreelanceWork.data.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.application
import androidx.lifecycle.viewModelScope
import com.sisterag.FreelanceWork.data.dabase.ApiDatabase
import com.sisterag.FreelanceWork.data.dabase.entities.PagoEntity
import com.sisterag.FreelanceWork.data.repository.PagoRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PagoViewModel(Application: Application)
    : AndroidViewModel(Application) {

    private val repository: PagoRepository
    var PagoEnti = PagoEntity()

    init {
        val pagoDao = ApiDatabase
            .getDatabase(this@PagoViewModel.application)
            .pagoDao()
        repository = PagoRepository(pagoDao)
    }

    fun ListaPago(): LiveData<List<PagoEntity>> {
        return repository.getAllPagos()
    }

    fun insertPago(pago: PagoEntity) =
        viewModelScope.launch(Dispatchers.IO) {

            repository.insertPago(pago)
    }

}