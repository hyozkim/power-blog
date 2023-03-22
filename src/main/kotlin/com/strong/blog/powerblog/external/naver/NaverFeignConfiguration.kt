package com.strong.blog.powerblog.external.naver

import com.strong.blog.powerblog.external.interceptor.NaverAuthRequestInterceptor
import feign.RequestInterceptor
import org.springframework.context.annotation.Bean

class NaverFeignConfiguration {
    @Bean
    fun requestTokenBearerInterceptor(): RequestInterceptor {
        return NaverAuthRequestInterceptor()
    }
}
