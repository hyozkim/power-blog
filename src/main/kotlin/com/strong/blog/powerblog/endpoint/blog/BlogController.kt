package com.strong.blog.powerblog.endpoint.blog

import com.strong.blog.powerblog.common.utils.Paged
import com.strong.blog.powerblog.domain.blog.service.BlogApplicationService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/blog")
class BlogController(
    private val blogApplicationService: BlogApplicationService,
) {
    @GetMapping("/search")
    fun search(
        @RequestParam query: String,
        @RequestParam(required = false) sort: String,
        @RequestParam(required = false) page: Int,
        @RequestParam(required = false) size: Int,
    ): Paged<Any> {
        val result = blogApplicationService.search(query, sort, page, size)

        return Paged(
            items = result.items,
            page = page,
            size = size,
            totalNumberOfItems = result.totalElements,
            totalNumberOfPages = result.totalPages,
            last = result.last,
        )
    }
}
