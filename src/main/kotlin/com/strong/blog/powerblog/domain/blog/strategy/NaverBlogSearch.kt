package com.strong.blog.powerblog.domain.blog.strategy

import com.strong.blog.powerblog.common.utils.OffsetPagedData
import com.strong.blog.powerblog.external.naver.NaverBlogFeignApi
import org.springframework.stereotype.Component

@Component
class NaverBlogSearch(
    private val naverBlogFeignApi: NaverBlogFeignApi,
) : BlogSearch {
    override fun search(query: String, sort: String, start: Int, display: Int): OffsetPagedData<Any> {
        val response = naverBlogFeignApi.search(query, if (sort == "accurancy") "sim" else "date", start, display)
        return OffsetPagedData(
            items = response.items,
            totalPages = response.total / display,
            totalElements = response.total,
            last = (response.total / display) - start <= 0,
        )
    }
}
