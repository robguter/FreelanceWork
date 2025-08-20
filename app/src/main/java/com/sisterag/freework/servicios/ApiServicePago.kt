package com.sisterag.FreelanceWork.servicios

import com.sisterag.FreelanceWork.data.dabase.entities.PagoEntity
import com.sisterag.FreelanceWork.utiles.EndPoint.ActPagoUrl
import com.sisterag.FreelanceWork.utiles.EndPoint.AddPagoUrl
import com.sisterag.FreelanceWork.utiles.EndPoint.DelPagoUrl
import com.sisterag.FreelanceWork.utiles.EndPoint.GAlPagoUrl
import com.sisterag.FreelanceWork.utiles.EndPoint.GIdPagoUrl
import retrofit2.Response
import retrofit2.http.DELETE
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface ApiServicePago {
    @GET(GAlPagoUrl)
    suspend fun getAllPago(): Response<List<PagoEntity>>

    @GET(GIdPagoUrl)
    suspend fun getByIdPago(
        @Path("id") id: String
    ): Response<PagoEntity>

    @FormUrlEncoded
    @POST(AddPagoUrl)
    suspend fun createPago(
        @Field("categoria") categoria: String,
        @Field("descripcion") descripcion: String
    ): Response<List<PagoEntity>>

    @FormUrlEncoded
    @PUT(ActPagoUrl)
    suspend fun updatePago(
        @Field("id") id: Int,
        @Field("categoria") categoria: String,
        @Field("descripcion") descripcion: String
    ): Response<List<PagoEntity>>

    @FormUrlEncoded
    @DELETE(DelPagoUrl)
    suspend fun deletePago(
        @Field("id") id: Int
    ): Response<List<PagoEntity>>
}