package com.sisterag.FreelanceWork.data.dabase.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.sisterag.FreelanceWork.data.dabase.entities.ProyectoEntity

@Dao
interface ProyectoDao {
    
    @Query("SELECT * FROM proyectos")
    fun getAllProyectos(): LiveData<List<ProyectoEntity>>
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProyecto(proyecto: ProyectoEntity)
}
