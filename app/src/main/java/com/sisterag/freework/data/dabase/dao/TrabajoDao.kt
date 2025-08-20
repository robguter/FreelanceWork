package com.sisterag.FreelanceWork.data.dabase.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.sisterag.FreelanceWork.data.dabase.entities.TrabajoEntity

@Dao
interface TrabajoDao {
    @Query("SELECT * FROM trabajos")
    fun getAllTrabajos(): LiveData<List<TrabajoEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTrabajo(trabajo: TrabajoEntity)
}
