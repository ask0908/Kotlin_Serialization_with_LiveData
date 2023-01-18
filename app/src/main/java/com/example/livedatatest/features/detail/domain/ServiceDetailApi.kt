package com.example.livedatatest.features.detail.domain

import retrofit2.http.GET
import retrofit2.http.Path

interface ServiceDetailApi {
    @GET("service/{serviceId}")
    suspend fun getServiceDetail(
        @Path("serviceId") serviceId: Int
    )
}