package com.sisterag.FreelanceWork.data.repository

import androidx.lifecycle.LiveData
import com.sisterag.FreelanceWork.data.dabase.dao.HabilidadDao
import com.sisterag.FreelanceWork.data.dabase.entities.HabilidadEntity

class HabilitaRepository(private val habilitaDao: HabilidadDao) {

    fun getAllHabilita(): LiveData<List<HabilidadEntity>> {
        return habilitaDao.getAllHabilidades()
    }

    suspend fun insertHabilita(habilita: HabilidadEntity) {
        habilitaDao.insertHabilidad(habilita)
    }

}