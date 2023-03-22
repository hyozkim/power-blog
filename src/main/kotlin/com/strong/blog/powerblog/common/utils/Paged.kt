package com.strong.blog.powerblog.common.utils

data class Paged<T>(
    val items: List<T>,
    val page: Int,
    val size: Int,
    val totalNumberOfItems: Int,
    val totalNumberOfPages: Int,
    val last: Boolean?
)
