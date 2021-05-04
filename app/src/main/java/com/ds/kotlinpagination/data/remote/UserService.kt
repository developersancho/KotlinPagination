package com.ds.kotlinpagination.data.remote

import com.ds.kotlinpagination.data.model.UsersResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface UserService {

    @GET(USERS)
    suspend fun getUsers(
        @Query("page") page: Int,
        @Query("per_page") perPage: Int
    ): UsersResponse


    companion object {
        const val USERS = "users"
    }

}