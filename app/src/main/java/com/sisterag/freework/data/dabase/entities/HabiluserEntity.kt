package com.sisterag.FreelanceWork.data.dabase.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Habilusers")
data class HabiluserEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Long? = null,
    @ColumnInfo(name = "freeid")
    var freeid: Int? = 0,
    @ColumnInfo(name = "hablid")
    var hablid: Int? = 0,
    @ColumnInfo(name = "nivel")
    var nivel: String? = ""
)