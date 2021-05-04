package com.ds.kotlinpagination.base

sealed class DataState<out T> {
    data class Success<out T>(val response: T) : DataState<T>()
    data class Error(val error: Throwable) : DataState<Nothing>()

    override fun toString(): String {
        return when (this) {
            is Success<*> -> "Success[data=$response]"
            is Error -> "Error[exception=$error]"
        }
    }
}