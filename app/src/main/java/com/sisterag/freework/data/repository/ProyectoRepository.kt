package com.sisterag.FreelanceWork.data.repository

import androidx.lifecycle.LiveData
import com.sisterag.FreelanceWork.data.dabase.dao.ProyectoDao
import com.sisterag.FreelanceWork.data.dabase.entities.ProyectoEntity

class ProyectoRepository(private val proyectoDao: ProyectoDao) {

    suspend fun insertProyecto(proyecto: ProyectoEntity) {
        proyectoDao.insertProyecto(proyecto)
    }

    fun getAllProyectos(): LiveData<List<ProyectoEntity>> {
        return proyectoDao.getAllProyectos()
    }

}