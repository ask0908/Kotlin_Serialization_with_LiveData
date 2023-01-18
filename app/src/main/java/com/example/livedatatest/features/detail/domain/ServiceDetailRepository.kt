package com.example.livedatatest.features.detail.domain

import com.example.livedatatest.base.BaseResult
import com.example.livedatatest.features.detail.domain.model.ServiceDetailModel

interface ServiceDetailRepository {
    suspend fun getServiceDetail(serviceId: Int): BaseResult<ServiceDetailModel>
}