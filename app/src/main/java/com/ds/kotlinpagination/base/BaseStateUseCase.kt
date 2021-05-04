package com.ds.kotlinpagination.base

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn

abstract class BaseStateUseCase<in Params, Result> {

    protected abstract suspend fun execute(params: Params): Flow<DataState<Result>>

    suspend operator fun invoke(params: Params): Flow<DataState<Result>> = execute(params)
        .flowOn(Dispatchers.IO)

}