package com.sisterag.FreelanceWork.data.repository

import androidx.lifecycle.LiveData
import com.sisterag.FreelanceWork.data.dabase.dao.HabiluserDao
import com.sisterag.FreelanceWork.data.dabase.entities.HabiluserEntity

class HabilUserRepository(private val habilUserDao: HabiluserDao) {

    suspend fun insertHabilUser(habilUser: HabiluserEntity) {
        habilUserDao.insertHabiluser(habilUser)
    }

    fun getAllHabilUsers(): LiveData<List<HabiluserEntity>> {
        return habilUserDao.getAllHabiluser()
    }

}