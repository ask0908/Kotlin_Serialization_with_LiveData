package com.example.livedatatest.features.detail.data.source

import com.example.livedatatest.base.BaseResult
import com.example.livedatatest.base.map
import com.example.livedatatest.features.detail.domain.ServiceDetailApi
import com.example.livedatatest.features.detail.domain.interactor.ServiceDetailResponseMapper
import com.example.livedatatest.features.detail.domain.model.ServiceDetailModel
import retrofit2.HttpException
import javax.inject.Inject

class ServiceDetailRemoteDataSource @Inject constructor(
    private val detailApi: ServiceDetailApi,
    private val mapper: ServiceDetailResponseMapper
) {
    companion object {
        private const val ERROR_NOT_FOUND = 404
    }

    suspend fun getServiceDetail(serviceId: Int): BaseResult<ServiceDetailModel> {
        return try {
            val response = detailApi.getServiceDetail(serviceId)
            response.map {
                mapper.map(it)
            }
        } catch (e: Exception) {
            when (e) {
                is HttpException -> if (e.code() == ERROR_NOT_FOUND) {
                    BaseResult.Error(Exception("요청한 서비스를 찾을 수 없습니다"))
                } else {
                    BaseResult.Error(e)
                }
                else -> BaseResult.Error(e)
            }
        }
    }
}