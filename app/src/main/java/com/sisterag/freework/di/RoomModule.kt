package com.sisterag.FreelanceWork.di

import android.content.Context
import androidx.room.Room

import com.sisterag.FreelanceWork.data.dabase.ApiDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    private const val DATABASE_NAME = "freework_database"

    @Singleton
    @Provides
    fun provideRoom(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, ApiDatabase::class.java, DATABASE_NAME).build()

    @Singleton
    @Provides
    fun caliApiDao(db: ApiDatabase) = db.calificaDao()

    @Singleton
    @Provides
    fun habilApiDao(db: ApiDatabase) = db.habilidadDao()

    @Singleton
    @Provides
    fun habusApiDao(db: ApiDatabase) = db.habiluserDao()

    @Singleton
    @Provides
    fun pagaApiDao(db: ApiDatabase) = db.pagoDao()

    @Singleton
    @Provides
    fun proyApiDao(db: ApiDatabase) = db.proyectoDao()

    @Singleton
    @Provides
    fun trabaApiDao(db: ApiDatabase) = db.trabajoDao()

    @Singleton
    @Provides
    fun userApiDao(db: ApiDatabase) = db.usuarioDao()

}