package com.example.tbc_couce_21.models

import com.squareup.moshi.Json

const val NETWORK_PAGE_SIZE = 6

data class InfoModel(
    val page: Int?,
    @Json(name = "per_page")
    val perPage: Int?,
    val total: Int?,
    @Json(name = "total_pages")
    val totalPages: Int?,
    val data: List<Data>,
    val support: Support?
)

data class Data(
    val id: Int?,
    val email: String?,
    @Json(name = "first_name")
    val firstName: String?,
    @Json(name = "last_name")
    val lastName: String?,
    val avatar: String?
)

data class Support(
    val url: String?,
    val text: String?
)