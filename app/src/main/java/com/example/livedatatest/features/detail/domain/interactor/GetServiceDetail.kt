package com.example.livedatatest.features.detail.domain.interactor

import com.example.livedatatest.base.BaseResult
import com.example.livedatatest.base.BaseUseCase
import com.example.livedatatest.features.detail.domain.ServiceDetailRepository
import com.example.livedatatest.features.detail.domain.model.ServiceDetailModel
import javax.inject.Inject

class GetServiceDetail @Inject constructor(
    private val serviceDetailRepository: ServiceDetailRepository
): BaseUseCase<ServiceDetailModel, GetServiceDetail.Params>() {
    override suspend fun getData(params: Params?): BaseResult<ServiceDetailModel> =
        params?.let {
            serviceDetailRepository.getServiceDetail(params.serviceId)
        } ?: run {
            BaseResult.Error(Exception("요청한 서비스를 찾을 수 없습니다"))
        }

    data class Params(
        val serviceId: Int
    )
}