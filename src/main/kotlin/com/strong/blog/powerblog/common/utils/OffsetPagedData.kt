package com.strong.blog.powerblog.common.utils

class OffsetPagedData<T> constructor(
    val items: List<T>,
    val totalPages: Int,
    val totalElements: Int,
    val last: Boolean
)
