package com.strong.blog.powerblog.external.interceptor

import feign.RequestInterceptor
import feign.RequestTemplate

/**
 * feign client에 `Authorization` header를 추가하는 interceptor
 */
class NaverAuthRequestInterceptor : RequestInterceptor {
    override fun apply(template: RequestTemplate) {
        template.header("X-Naver-Client-Id", "$X_NAVER_CLIENT_ID")
        template.header("X-Naver-Client-Secret", "$X_NAVER_CLIENT_SECRET")
    }

    companion object {
        const val X_NAVER_CLIENT_ID = "XtF1W2Q5emLFga3N4wv0"
        const val X_NAVER_CLIENT_SECRET = "FQ6xr5ts1c"
    }
}
