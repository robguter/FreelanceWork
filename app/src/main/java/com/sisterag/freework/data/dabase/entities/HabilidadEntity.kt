package com.sisterag.FreelanceWork.data.dabase.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "habilidades")
data class HabilidadEntity(
    @PrimaryKey(autoGenerate = true)
    var hablid : Long? = null,
    @ColumnInfo(name = "nombre" )
    var nombre : String? = ""
)