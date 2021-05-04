package com.ds.kotlinpagination.data.repository

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.ds.kotlinpagination.base.DEFAULT_PAGE_INDEX
import com.ds.kotlinpagination.data.model.User
import com.ds.kotlinpagination.data.remote.UserService
import retrofit2.HttpException
import java.io.IOException

class UserPagingSource(private val service: UserService) : PagingSource<Int, User>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, User> {
        //for first case it will be null, then we can pass some default value, in our case it's 1
        val page = params.key ?: DEFAULT_PAGE_INDEX
        return try {
            val response = service.getUsers(page, params.loadSize)
            val userList = response.users.orEmpty()

            LoadResult.Page(
                data = userList,
                prevKey = if (page == DEFAULT_PAGE_INDEX) null else page - 1,
                nextKey = if (userList.isEmpty()) null else page + 1
            )

        } catch (exception: IOException) {
            // IOException for network failures.
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            // HttpException for any non-2xx HTTP status codes.
            return LoadResult.Error(exception)
        }
    }

    // The refresh key is used for subsequent refresh calls to PagingSource.load after the initial load
    override fun getRefreshKey(state: PagingState<Int, User>): Int? {
        // Try to find the page key of the closest page to anchorPosition, from
        // either the prevKey or the nextKey, but you need to handle nullability
        // here:
        //  * prevKey == null -> anchorPage is the first page.
        //  * nextKey == null -> anchorPage is the last page.
        //  * both prevKey and nextKey null -> anchorPage is the initial page, so
        //    just return null.
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }
}