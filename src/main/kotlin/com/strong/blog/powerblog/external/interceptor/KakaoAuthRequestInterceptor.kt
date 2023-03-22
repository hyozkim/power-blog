package com.strong.blog.powerblog.external.interceptor

import feign.RequestInterceptor
import feign.RequestTemplate

/**
 * feign client에 `Authorization` header를 추가하는 interceptor
 */
class KakaoAuthRequestInterceptor : RequestInterceptor {

    override fun apply(template: RequestTemplate) {
        template.header("Authorization", "KakaoAK $KAKAO_REST_API_KEY")
    }

    companion object {
        const val KAKAO_REST_API_KEY = "97baa0b614b0cff3ba0c550e515bfffb"
    }
}
