package com.strong.blog.powerblog.external.kakao.dto

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty

data class KakaoBlogResponseDto @JsonCreator constructor(
    val meta: Meta,
    val documents: List<Document>
)

data class Meta(
    @JsonProperty("total_count")
    val totalCount: Int,
    @JsonProperty("pageable_count")
    val pageableCount: Int,
    @JsonProperty("is_end")
    val isEnd: Boolean
)

data class Document(
    @JsonProperty("title")
    val title: String,
    @JsonProperty("contents")
    val contents: String,
    @JsonProperty("url")
    val url: String,
    @JsonProperty("blogname")
    val blogName: String,
    @JsonProperty("thumbnail")
    val thumbnail: String,
    @JsonProperty("datetime")
    val datetime: String
)
