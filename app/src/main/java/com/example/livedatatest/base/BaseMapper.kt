package com.example.livedatatest.base

interface BaseMapper<T, R> {
    fun map(from: T): R
}

fun <T, R> T.map(mapper: (T) -> R) =
    BaseResult.Success(
        mapper(this)
    )