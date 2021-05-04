package com.ds.kotlinpagination.base

open class BaseRepository {

    protected suspend fun <T : Any> apiCall(call: suspend () -> T): DataState<T> {
        return try {
            val response = call()
            DataState.Success(response)
        } catch (ex: Throwable) {
            DataState.Error(ex)
        }
    }

}