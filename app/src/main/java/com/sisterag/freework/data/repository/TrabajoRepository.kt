package com.sisterag.FreelanceWork.data.repository

import androidx.lifecycle.LiveData
import com.sisterag.FreelanceWork.data.dabase.dao.TrabajoDao
import com.sisterag.FreelanceWork.data.dabase.entities.TrabajoEntity

class TrabajoRepository(private val trabajoDao: TrabajoDao) {

    suspend fun insertTrabajo(trabajo: TrabajoEntity) {
        trabajoDao.insertTrabajo(trabajo)
    }

    fun getAllTrabajos(): LiveData<List<TrabajoEntity>> {
        return trabajoDao.getAllTrabajos()
    }
}