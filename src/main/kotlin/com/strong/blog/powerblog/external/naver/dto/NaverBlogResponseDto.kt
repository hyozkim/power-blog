package com.strong.blog.powerblog.external.naver.dto

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import java.time.LocalDateTime

data class NaverBlogResponseDto @JsonCreator constructor(
    @JsonProperty("lastBuildDate")
    val lastBuildDate: String,
    @JsonProperty("total")
    val total: Int,
    @JsonProperty("start")
    val start: Int,
    @JsonProperty("display")
    val display: Int,
    @JsonProperty("items")
    val items: List<Item>
)

data class Rss(
    @JsonProperty("channel")
    val channel: Channel,
    @JsonProperty("items")
    val items: List<Item>
)

data class Channel(
    @JsonProperty("lastBuildDate")
    val lastBuildDate: LocalDateTime,
    @JsonProperty("total")
    val total: Int,
    @JsonProperty("start")
    val start: Int,
    @JsonProperty("display")
    val display: Int
)

data class Item(
    @JsonProperty("title")
    val title: String,
    @JsonProperty("link")
    val link: String,
    @JsonProperty("description")
    val description: String,
    @JsonProperty("bloggername")
    val bloggerName: String,
    @JsonProperty("bloggerlink")
    val bloggerLink: String,
    @JsonProperty("postdate")
    val postdate: String
)
