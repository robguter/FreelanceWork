package com.sisterag.FreelanceWork.servicios

import com.sisterag.FreelanceWork.data.dabase.entities.UsuarioEntity
import com.sisterag.FreelanceWork.utiles.EndPoint.ActUserUrl
import com.sisterag.FreelanceWork.utiles.EndPoint.AddUserUrl
import com.sisterag.FreelanceWork.utiles.EndPoint.DelUserUrl
import com.sisterag.FreelanceWork.utiles.EndPoint.GAlUserUrl
import com.sisterag.FreelanceWork.utiles.EndPoint.GIdUserUrl
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.DELETE
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Part
import retrofit2.http.Path

interface ApiServiceUsr {

    @GET(GAlUserUrl)
    suspend fun getAllUsr():
    Response<List<UsuarioEntity>>

    @GET(GIdUserUrl)
    suspend fun getByIdUsr(
        @Path("id") id: String
    ): Response<UsuarioEntity>

    @Multipart
    @POST(AddUserUrl)
    suspend fun createUsr(  //uploadImage
        @Part("nombre") nombre: RequestBody,
        @Part("apellido") apellido: RequestBody,
        @Part("telefono") telefono: RequestBody,
        @Part("email") email: RequestBody,
        @Part("usuario") usuario: RequestBody,
        @Part("clave") clave: RequestBody,
        @Part("tipouser") tipouser: RequestBody,
        @Part("informacion") informacion: RequestBody,
        @Part("estatus") estatus: RequestBody,
        @Part imagen: MultipartBody.Part
    ): Response<UsuarioEntity>

    @FormUrlEncoded
    @PUT(ActUserUrl)
    suspend fun updateUsr(
        @Field("id") id: Int,
        @Field("idcat") idcat: Int,
        @Field("articulo") articulo: String,
        @Field("marca") marca: String,
        @Field("descripcion") descripcion: String,
        @Field("image") image: String
    ): Response<List<UsuarioEntity>>

    @FormUrlEncoded
    @DELETE(DelUserUrl)
    suspend fun deleteUsr(
        @Field("id") id: Int
    ): Response<List<UsuarioEntity>>

}