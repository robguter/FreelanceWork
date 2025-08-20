package com.sisterag.FreelanceWork.servicios

import com.sisterag.FreelanceWork.data.dabase.entities.UsuarioEntity
import com.sisterag.FreelanceWork.utiles.EndPoint.ActProyUrl
import com.sisterag.FreelanceWork.utiles.EndPoint.AddProyUrl
import com.sisterag.FreelanceWork.utiles.EndPoint.DelProyUrl
import com.sisterag.FreelanceWork.utiles.EndPoint.GAlProyUrl
import com.sisterag.FreelanceWork.utiles.EndPoint.GIdProyUrl
import retrofit2.Response
import retrofit2.http.DELETE
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface ApiServiceProy {
    @GET(GAlProyUrl)
    suspend fun getAllProy(): Response<List<UsuarioEntity>>

    @GET(GIdProyUrl)
    suspend fun getByIdProy(
        @Path("id") id: String
    ): Response<UsuarioEntity>

    @FormUrlEncoded
    @POST(AddProyUrl)
    suspend fun createProy(
        @Field("nombre") nombre: String,
        @Field("telefono") telefono: String,
        @Field("apodo") apodo: String
    ): Response<List<UsuarioEntity>>

    @FormUrlEncoded
    @PUT(ActProyUrl)
    suspend fun updateProy(
        @Field("id") id: Int,
        @Field("nombre") nombre: String,
        @Field("telefono") telefono: String,
        @Field("apodo") apodo: String
    ): Response<List<UsuarioEntity>>

    @FormUrlEncoded
    @DELETE(DelProyUrl)
    suspend fun deleteProy(
        @Field("id") id: Int
    ): Response<List<UsuarioEntity>>

}