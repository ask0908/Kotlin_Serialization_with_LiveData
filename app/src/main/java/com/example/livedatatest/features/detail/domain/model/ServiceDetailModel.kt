package com.example.livedatatest.features.detail.domain.model

data class ServiceDetailModel(
    val id: Int,
    val serviceId: Int,
    val name: String,
    val longName: String? = null,
    val imageUrl: String? = null,
    val proCount: Int? = 0,
    val averageRating: Float? = 0f,
    val jobCountOnLastMonth: Int? = 0
)