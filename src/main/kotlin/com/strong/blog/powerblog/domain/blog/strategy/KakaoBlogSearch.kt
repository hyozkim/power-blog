package com.strong.blog.powerblog.domain.blog.strategy

import com.strong.blog.powerblog.common.utils.OffsetPagedData
import com.strong.blog.powerblog.external.kakao.KakaoBlogFeignApi
import org.springframework.stereotype.Component

@Component
class KakaoBlogSearch(
    private val kakaoBlogFeignApi: KakaoBlogFeignApi,
) : BlogSearch {
    override fun search(query: String, sort: String, page: Int, size: Int): OffsetPagedData<Any> {
        val response = kakaoBlogFeignApi.search(query, sort, page, size)
        return OffsetPagedData(
            items = response.documents,
            totalPages = response.meta.pageableCount / size,
            totalElements = response.meta.totalCount,
            last = response.meta.isEnd,
        )
    }
}
