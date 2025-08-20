package com.sisterag.FreelanceWork.data.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.application
import androidx.lifecycle.viewModelScope
import com.sisterag.FreelanceWork.data.dabase.ApiDatabase
import com.sisterag.FreelanceWork.data.dabase.entities.HabiluserEntity
import com.sisterag.FreelanceWork.data.repository.HabilUserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HabilUserViewModel(Application: Application)
    : AndroidViewModel(Application) {

        private val repository: HabilUserRepository
        var habiluserEnti = HabiluserEntity()

    init {
        val habilUserDao = ApiDatabase
            .getDatabase(this@HabilUserViewModel.application)
            .habiluserDao()
        repository = HabilUserRepository(habilUserDao)
    }

    fun ListaHabilUser(): LiveData<List<HabiluserEntity>> {
        return repository.getAllHabilUsers()
    }

    fun insertHabilUser(habilUser: HabiluserEntity) =
        viewModelScope.launch(Dispatchers.IO) {

            repository.insertHabilUser(habilUser)
        }
}