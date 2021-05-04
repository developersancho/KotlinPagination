package com.ds.kotlinpagination.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.ds.kotlinpagination.base.BaseRepository
import com.ds.kotlinpagination.base.DataState
import com.ds.kotlinpagination.base.NETWORK_PAGE_SIZE
import com.ds.kotlinpagination.data.model.User
import com.ds.kotlinpagination.data.model.UsersResponse
import com.ds.kotlinpagination.data.remote.UserService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class UserRepository(private val service: UserService) : BaseRepository() {

    fun getUserList(page: Int, perPage: Int): Flow<DataState<UsersResponse>> =
        flow { emit(apiCall { service.getUsers(page, perPage) }) }

    fun getUsers(): Flow<PagingData<User>> {
        return Pager(
            config = PagingConfig(
                pageSize = NETWORK_PAGE_SIZE,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { UserPagingSource(service) }
        ).flow
    }

}