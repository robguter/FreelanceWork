package com.sisterag.FreelanceWork.data.repository

import androidx.lifecycle.LiveData
import com.sisterag.FreelanceWork.data.dabase.dao.CalificaDao
import com.sisterag.FreelanceWork.data.dabase.entities.CalificaEntity

class CalificaRepository(private val calificaDao: CalificaDao) {

    fun getAllCalifica(): LiveData<List<CalificaEntity>> {
        return calificaDao.getAllCalifica()
    }

    suspend fun insertCalifica(califica: CalificaEntity) {
        calificaDao.insertCalifica(califica)
    }

}