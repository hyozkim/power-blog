package com.strong.blog.powerblog.endpoint.blog.dto

data class BlogResponseDto(
    val title: String,
    val contents: String,
    val url: String,
    val blogName: String,
    val thumbnail: String,
    val datetime: String
)
