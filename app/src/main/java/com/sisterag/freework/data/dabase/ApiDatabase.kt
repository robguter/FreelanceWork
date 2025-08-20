package com.sisterag.FreelanceWork.data.dabase

import android.content.Context
import androidx.room.TypeConverters
import androidx.room.Database
import androidx.room.RoomDatabase
import com.sisterag.FreelanceWork.data.dabase.dao.CalificaDao
import com.sisterag.FreelanceWork.data.dabase.dao.HabilidadDao
import com.sisterag.FreelanceWork.data.dabase.dao.HabiluserDao
import com.sisterag.FreelanceWork.data.dabase.dao.PagoDao
import com.sisterag.FreelanceWork.data.dabase.dao.ProyectoDao
import com.sisterag.FreelanceWork.data.dabase.dao.TrabajoDao
import com.sisterag.FreelanceWork.data.dabase.dao.UsuarioDao
import com.sisterag.FreelanceWork.data.dabase.entities.CalificaEntity
import com.sisterag.FreelanceWork.data.dabase.entities.HabilidadEntity
import com.sisterag.FreelanceWork.data.dabase.entities.HabiluserEntity
import com.sisterag.FreelanceWork.data.dabase.entities.PagoEntity
import com.sisterag.FreelanceWork.data.dabase.entities.ProyectoEntity
import com.sisterag.FreelanceWork.data.dabase.entities.TrabajoEntity
import com.sisterag.FreelanceWork.data.dabase.entities.UsuarioEntity


@Database(entities = [CalificaEntity::class, HabilidadEntity::class,
    HabiluserEntity::class, PagoEntity::class, ProyectoEntity::class,
    TrabajoEntity::class, UsuarioEntity::class], version = 4 )
abstract class ApiDatabase: RoomDatabase() {

    abstract fun calificaDao(): CalificaDao
    abstract fun habilidadDao(): HabilidadDao
    abstract fun habiluserDao(): HabiluserDao
    abstract fun pagoDao(): PagoDao
    abstract fun proyectoDao(): ProyectoDao
    abstract fun trabajoDao(): TrabajoDao
    abstract fun usuarioDao(): UsuarioDao


    companion object {
        @Volatile
        private var INSTANCE: ApiDatabase? = null

        fun getDatabase(context: Context): ApiDatabase {
            val tmpInstance = INSTANCE
            if (tmpInstance != null) {
                return tmpInstance
            }

            synchronized(this) {
                val instance = androidx.room.Room.databaseBuilder(
                    context.applicationContext,
                    ApiDatabase::class.java,
                    "api_database"
                )
                //.fallbackToDestructiveMigration()
                .build()
                INSTANCE = instance
                //instance.clearAllTables()
                return instance
            }
        }
    }

}