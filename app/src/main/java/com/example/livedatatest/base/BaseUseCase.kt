package com.example.livedatatest.base

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

abstract class BaseUseCase<T, Params> {
    abstract suspend fun getData(params: Params?): BaseResult<T>

    suspend fun execute(params: Params? = null): Flow<BaseResult<T>> = flow {
        // 요청을 시작할 때 프로그레스 바 표시
        emit(BaseResult.Loading(showing = true))

        // 실제 결과 방출
        emit(getData(params))

        // 요청이 완료되면 프로그레스 바 숨김
        emit(BaseResult.Loading(showing = false))
    }
}