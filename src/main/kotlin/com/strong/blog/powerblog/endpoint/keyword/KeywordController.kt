package com.strong.blog.powerblog.endpoint.keyword

import com.strong.blog.powerblog.domain.keyword.service.KeywordQueryService
import com.strong.blog.powerblog.endpoint.keyword.dto.KeywordResponseDto
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/keyword")
class KeywordController(
    private val keywordQueryService: KeywordQueryService,
) {
    @GetMapping("/top-searched")
    fun topSearched() = keywordQueryService.getTopSearched().map { KeywordResponseDto.from(it) }
}
