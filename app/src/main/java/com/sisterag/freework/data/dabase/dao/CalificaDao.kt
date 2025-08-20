package com.sisterag.FreelanceWork.data.dabase.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.sisterag.FreelanceWork.data.dabase.entities.CalificaEntity

@Dao
interface CalificaDao {

    @Query("SELECT * FROM calificaciones")
    fun getAllCalifica(): LiveData<List<CalificaEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCalifica(califica: CalificaEntity)


}