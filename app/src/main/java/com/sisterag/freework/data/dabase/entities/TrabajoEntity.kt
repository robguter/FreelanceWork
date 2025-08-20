package com.sisterag.FreelanceWork.data.dabase.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate
import java.util.Date

@Entity(tableName = "trabajos")
data class TrabajoEntity(

    @PrimaryKey(autoGenerate = true)
    var trabid: Long? = null,
    @ColumnInfo(name = "proyid")
    var proyid: Int? = null,
    @ColumnInfo(name = "freeid")
    var freeid: Int? = null,
    @ColumnInfo(name = "descripcion")
    var descripcion : String? = null,
    @ColumnInfo(name = "presupuesto")
    var presupuesto : Int? = null,
    @ColumnInfo(name = "fechainicio")
    var fechainicio : String? = null,
    @ColumnInfo(name = "fechafin")
    var fechafin: String? = null,
    @ColumnInfo(name = "estado")
    var estado: String? = null
)