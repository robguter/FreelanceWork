package com.sisterag.FreelanceWork.data.dabase.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "usuarios")
data class UsuarioEntity(

    @PrimaryKey(autoGenerate = true)
    var userid: Long? = null,
    @ColumnInfo(name = "nombre")
    var nombre: String? = null,
    @ColumnInfo(name = "apellido")
    var apellido: String? = null,
    @ColumnInfo(name = "telefono")
    var telefono: String? = null,
    @ColumnInfo(name = "email" )
    var email : String? = null,
    @ColumnInfo(name = "usuario" )
    var usuario : String? = null,
    @ColumnInfo(name = "tipouser")
    var tipouser: String? = null,
    @ColumnInfo(name = "informacion" )
    var informacion : String? = null,
    @ColumnInfo(name = "estatus" )
    var estatus : String? = null,
    @ColumnInfo(name = "imagen")
    var imagen: String? = null,
    var fullname: String? = null
) {
    init {
  fullname = "$nombre $apellido"
    }
}