package com.strong.blog.powerblog.endpoint.keyword.dto

import com.strong.blog.powerblog.domain.keyword.model.Keyword

data class KeywordResponseDto(
    val query: String,
    val count: Long
) {
    companion object {
        fun from(keyword: Keyword) = KeywordResponseDto(
            query = keyword.query,
            count = keyword.searchCount
        )
    }
}
