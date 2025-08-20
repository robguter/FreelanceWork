package com.sisterag.FreelanceWork.data.dabase.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.sisterag.FreelanceWork.data.dabase.entities.HabilidadEntity

@Dao
interface HabilidadDao {
    @Query("SELECT * FROM habilidades")
    fun getAllHabilidades(): LiveData<List<HabilidadEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertHabilidad(habilidad: HabilidadEntity)

}