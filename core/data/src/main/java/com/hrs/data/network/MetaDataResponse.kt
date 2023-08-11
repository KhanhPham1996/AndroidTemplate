package com.hrs.data.network

import com.hrs.extentions.*
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
open class MetaDataResponse(
    @Json(name = "firstPage") val firstPage: Int?,
    @Json(name = "currentPage") val currentPage: Int?,
    @Json(name = "totalPages") val totalPages: Int?,
    @Json(name = "nextPage") val nextPage: Int?,
    @Json(name = "lastPage") val lastPage: Int?,
    @Json(name = "totalRecords") val totalRecords: Int?,
    @Json(name = "pageSize") val pageSize: Int?,

    @Json(name = "totalUnseen") val totalUnseen: Int?,
    @Json(name = "lastItem") val lastItem: Boolean?
) {

    fun <K> toPage() = Page<K>(
        firstPage = firstPage.orZero(),
        currentPage = currentPage.orZero(),
        totalPages = totalPages.orZero(),
        nextPage = nextPage.orZero(),
        lastPage = lastPage.orZero(),
        totalRecords = totalRecords.orZero(),
        pageSize = pageSize.orZero(),
        totalUnseen = totalUnseen.orZero(),
        lastItem = lastItem.orFalse()
    )
}