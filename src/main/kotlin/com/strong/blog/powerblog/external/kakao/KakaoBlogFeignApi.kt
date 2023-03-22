package com.strong.blog.powerblog.external.kakao

import com.strong.blog.powerblog.external.kakao.dto.KakaoBlogResponseDto
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam

/**
 * https://developers.kakao.com/docs/latest/ko/daum-search/dev-guide#search-blog
 * 해당 문서 참조
 */
@FeignClient(
    name = "kakao-blog-api",
    url = "https://dapi.kakao.com",
    configuration = [KakaoFeignConfiguration::class]

)
interface KakaoBlogFeignApi {
    @GetMapping("/v2/search/blog")
    fun search(
        @RequestParam query: String,
        @RequestParam(required = false) sort: String,
        @RequestParam(required = false) page: Int,
        @RequestParam(required = false) size: Int
    ): KakaoBlogResponseDto
}
