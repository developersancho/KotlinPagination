package com.ds.kotlinpagination.paging3

import com.ds.kotlinpagination.data.model.User

sealed class UserUiModel {
    data class UserItem(val user: User) : UserUiModel()
    data class SeparatorItem(val description: String) : UserUiModel()
}
