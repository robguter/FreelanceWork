package com.sisterag.FreelanceWork.data.dabase.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.sisterag.FreelanceWork.data.dabase.entities.PagoEntity

@Dao
interface PagoDao {
    
    @Query("SELECT * FROM pagos")
    fun getAllPagos(): LiveData<List<PagoEntity>>
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPago(pago: PagoEntity)
}
