package com.strong.blog.powerblog.domain.keyword.repository

import com.strong.blog.powerblog.domain.keyword.model.Keyword
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Lock
import org.springframework.stereotype.Repository
import javax.persistence.LockModeType

@Repository
interface KeywordRepository : JpaRepository<Keyword, Long> {
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    fun findByQuery(query: String): Keyword?

    fun findTop10ByOrderBySearchCountDesc(): List<Keyword>
}
