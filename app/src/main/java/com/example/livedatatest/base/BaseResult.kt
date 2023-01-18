package com.example.livedatatest.base

sealed class BaseResult<out R> {
    data class Success<out T>(val data: T): BaseResult<T>()
    data class Error(val exception: Exception): BaseResult<Nothing>()
    data class Loading(val showing: Boolean): BaseResult<Nothing>()
}

/**
 * [BaseResult]가 [Success] 타입이고 null이 아닌 [Success.data]를 갖고 있으면 true
 */
val BaseResult<*>.succeeded
    get() = this is BaseResult.Success && data != null
