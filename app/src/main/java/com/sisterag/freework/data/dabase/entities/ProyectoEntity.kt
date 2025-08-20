package com.sisterag.FreelanceWork.data.dabase.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "proyectos")
data class ProyectoEntity(

    @PrimaryKey(autoGenerate = true)
    var proyid: Long? = null,
    @ColumnInfo(name = "cnteid")
    var cnteid: Int? = null,
    @ColumnInfo(name = "titulo")
    var titulo: String? = null,
    @ColumnInfo(name = "descripcion")
    var descripcion: String? = null,
    @ColumnInfo(name = "presupuesto")
    var presupuesto: Int? = null,
    @ColumnInfo(name = "fechacreacion")
    var fechacreacion: String? = null,
    @ColumnInfo(name = "fechalimite")
    var fechalimite: String? = null,
    @ColumnInfo(name = "estado")
    var estado: String? = null
)