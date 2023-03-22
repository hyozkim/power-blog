package com.strong.blog.powerblog.domain.blog.strategy

import com.strong.blog.powerblog.common.utils.OffsetPagedData

interface BlogSearch {
    fun search(query: String, sort: String, page: Int, size: Int): OffsetPagedData<Any>
}
