package com.strong.blog.powerblog.external.naver

import com.strong.blog.powerblog.external.naver.dto.NaverBlogResponseDto
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam

/**
* https://developers.naver.com/docs/serviceapi/search/blog/blog.md
* 해당 문서 참조
*/
@FeignClient(
    name = "naver-blog-api",
    url = "https://openapi.naver.com",
    configuration = [NaverFeignConfiguration::class]

)
interface NaverBlogFeignApi {
    @GetMapping("/v1/search/blog.json")
    fun search(
        @RequestParam query: String,
        @RequestParam(required = false) sort: String,
        @RequestParam(required = false) start: Int,
        @RequestParam(required = false) display: Int
    ): NaverBlogResponseDto
}
