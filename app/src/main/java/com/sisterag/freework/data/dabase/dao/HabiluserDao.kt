package com.sisterag.FreelanceWork.data.dabase.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.sisterag.FreelanceWork.data.dabase.entities.HabiluserEntity

@Dao
interface HabiluserDao {
    @Query("SELECT * FROM habilusers")
    fun getAllHabiluser(): LiveData<List<HabiluserEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertHabiluser(habiluser: HabiluserEntity)
}
