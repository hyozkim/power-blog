package com.strong.blog.powerblog.domain.keyword.model

import org.hibernate.annotations.DynamicInsert
import org.hibernate.annotations.DynamicUpdate
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.EntityListeners
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Table
import javax.persistence.Version

@Entity
@Table(
    name = "keyword"
)
@EntityListeners(AuditingEntityListener::class)
@DynamicInsert
@DynamicUpdate
data class Keyword(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0,

    @Column(name = "query", unique = true, nullable = false)
    var query: String = "",

    @Column(name = "search_count", nullable = false)
    var searchCount: Long = 0,

    @CreatedDate
    @Column(name = "created_at", nullable = false)
    var createdAt: LocalDateTime? = null,

    @LastModifiedDate
    @Version
    @Column(name = "modified_at", nullable = false)
    var modifiedAt: LocalDateTime? = null,

    var deleted: Boolean = false
) {
    fun increaseCount(): Long {
        return ++this.searchCount
    }
}
