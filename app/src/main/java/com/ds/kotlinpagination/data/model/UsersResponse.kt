package com.ds.kotlinpagination.data.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class UsersResponse(
    @Json(name = "data")
    var users: List<User>?,
    @Json(name = "page")
    var page: Int?,
    @Json(name = "per_page")
    var perPage: Int?,
    @Json(name = "support")
    var support: Support?,
    @Json(name = "total")
    var total: Int?,
    @Json(name = "total_pages")
    var totalPages: Int?
)

@JsonClass(generateAdapter = true)
data class User(
    @Json(name = "avatar")
    var avatar: String?,
    @Json(name = "email")
    var email: String?,
    @Json(name = "first_name")
    var firstName: String?,
    @Json(name = "id")
    var id: Int?,
    @Json(name = "last_name")
    var lastName: String?
)

@JsonClass(generateAdapter = true)
data class Support(
    @Json(name = "text")
    var text: String?,
    @Json(name = "url")
    var url: String?
)