package com.strong.blog.powerblog.domain.blog.service

import com.strong.blog.powerblog.common.utils.OffsetPagedData
import com.strong.blog.powerblog.domain.blog.strategy.KakaoBlogSearch
import com.strong.blog.powerblog.domain.blog.strategy.NaverBlogSearch
import com.strong.blog.powerblog.domain.keyword.model.Keyword
import com.strong.blog.powerblog.domain.keyword.repository.KeywordRepository
import mu.KotlinLogging
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Isolation
import org.springframework.transaction.annotation.Transactional

@Service
class BlogApplicationService(
    private val kakaoBlogSearch: KakaoBlogSearch,
    private val naverBlogSearch: NaverBlogSearch,
    private val keywordRepository: KeywordRepository,
) {
    @Transactional
    fun search(query: String, sort: String, page: Int, size: Int): OffsetPagedData<Any> {
        val response = try {
            kakaoBlogSearch.search(query, sort, page, size)
        } catch (e: Exception) {
            naverBlogSearch.search(query, sort, page, size)
        }
        keywordRepository.findByQuery(query)
            ?.let { keyword ->
                log.info { "keyword: $keyword" }
                keywordRepository.save(
                    keyword.apply {
                        searchCount = keyword.increaseCount()
                    },
                )
                log.info { "keyword: $keyword" }
            } ?: keywordRepository.save(Keyword(query = query, searchCount = 1L))
        return response
    }

    companion object {
        private val log = KotlinLogging.logger { }
    }
}
