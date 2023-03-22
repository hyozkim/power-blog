package com.strong.blog.powerblog.external.kakao

import com.strong.blog.powerblog.external.interceptor.KakaoAuthRequestInterceptor
import feign.RequestInterceptor
import org.springframework.context.annotation.Bean

class KakaoFeignConfiguration {
    @Bean
    fun requestTokenBearerInterceptor(): RequestInterceptor {
        return KakaoAuthRequestInterceptor()
    }
}
