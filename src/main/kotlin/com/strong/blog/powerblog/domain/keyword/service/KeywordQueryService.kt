package com.strong.blog.powerblog.domain.keyword.service

import com.strong.blog.powerblog.domain.keyword.model.Keyword
import com.strong.blog.powerblog.domain.keyword.repository.KeywordRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class KeywordQueryService(
    private val keywordRepository: KeywordRepository,
) {
    @Transactional(readOnly = true)
    fun getTopSearched(): List<Keyword> {
        return keywordRepository.findTop10ByOrderBySearchCountDesc()
    }
}
