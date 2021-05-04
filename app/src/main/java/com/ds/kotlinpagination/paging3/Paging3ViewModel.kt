package com.ds.kotlinpagination.paging3

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.insertSeparators
import androidx.paging.map
import com.ds.kotlinpagination.data.model.User
import com.ds.kotlinpagination.data.repository.UserRepository
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class Paging3ViewModel(private val userRepository: UserRepository) : ViewModel() {

    private val _viewState = MutableStateFlow<PagingData<User>>(PagingData.empty())
    val viewState: StateFlow<PagingData<User>> = _viewState

    private var currentUserResult: Flow<PagingData<User>>? = null

    fun getUsers() = viewModelScope.launch {
        userRepository.getUsers().collect {
            Log.d("GETUSERS:::", "I am collected")
            _viewState.value = it
        }
    }

    fun getUserList(): Flow<PagingData<UserUiModel>> {
        return userRepository.getUsers()
            .map { pagingData -> pagingData.map { UserUiModel.UserItem(it) } }
            .map {
                it.insertSeparators { before, after ->
                    if (after == null) {
                        // we're at the end of the list
                        return@insertSeparators null
                    }

                    if (before == null) {
                        // we're at the beginning of the list
                        return@insertSeparators null
                    }

                    // check between 2 items
                    if (before.user.id != after.user.id) {
                        UserUiModel.SeparatorItem("**********************************")
                    } else {
                        // no separator
                        null
                    }
                }
            }
            .cachedIn(viewModelScope)
    }

}