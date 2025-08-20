package com.sisterag.FreelanceWork.data.dabase.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "pagos")
data class PagoEntity(

    @PrimaryKey(autoGenerate = true)
    var pagoid: Long? = null,
    @ColumnInfo(name = "trabid")
    var trabid: Int? = null,
    @ColumnInfo(name = "monto")
    var monto: Int?    = null,
    @ColumnInfo(name = "fechapago")
    var fechapago: String? = null,
    @ColumnInfo(name = "metodopago")
    var metodopago: String? = null,
    @ColumnInfo(name = "estado")
    var estado: String? = null
)