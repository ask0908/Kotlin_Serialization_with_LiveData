package com.example.livedatatest.features.detail.domain.interactor

import com.example.livedatatest.base.BaseMapper
import com.example.livedatatest.features.detail.data.response.ServiceDetailResponse
import com.example.livedatatest.features.detail.domain.model.ServiceDetailModel

object ServiceDetailResponseMapper: BaseMapper<ServiceDetailResponse, ServiceDetailModel> {
    override fun map(from: ServiceDetailResponse): ServiceDetailModel =
        ServiceDetailModel(
            id = from.id,
            serviceId = from.serviceId,
            name = from.name,
            longName = from.longName,
            imageUrl = from.imageUrl,
            proCount = from.proCount,
            averageRating = from.averageRating,
            jobCountOnLastMonth = from.jobCountOnLastMonth
        )
}