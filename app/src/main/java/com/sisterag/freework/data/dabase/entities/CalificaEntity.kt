package com.sisterag.FreelanceWork.data.dabase.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "calificaciones")
data class CalificaEntity(

    @PrimaryKey(autoGenerate = true)
    val caliid: Long? = null,
    @ColumnInfo(name = "trabid" )
    var trabid: Int? = null,
    @ColumnInfo(name = "calificadorid" )
    var calificadorid: Int? = null,
    @ColumnInfo(name = "calificadoid" )
    var calificadoid: Int? = null,
    @ColumnInfo(name = "puntuacion" )
    var puntuacion: Int? = null,
    @ColumnInfo(name = "comentario" )
    var comentario: String? = null,
    @ColumnInfo(name = "fechacali" )
    var fechacali: String? = null
)
