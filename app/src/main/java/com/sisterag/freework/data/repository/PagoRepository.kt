package com.sisterag.FreelanceWork.data.repository

import androidx.lifecycle.LiveData
import com.sisterag.FreelanceWork.data.dabase.dao.PagoDao
import com.sisterag.FreelanceWork.data.dabase.entities.PagoEntity

class PagoRepository(private val pagoDao: PagoDao) {

    suspend fun insertPago(pago: PagoEntity) {
        pagoDao.insertPago(pago)
    }

    fun getAllPagos(): LiveData<List<PagoEntity>> {
        return pagoDao.getAllPagos()
    }
}