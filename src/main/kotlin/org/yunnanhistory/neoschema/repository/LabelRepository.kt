package org.yunnanhistory.neoschema.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import org.yunnanhistory.neoschema.domain.sql.Label

@Repository
interface LabelRepository: JpaRepository<Label, Long> {
    fun findByType(type: String): Label?
    fun findByTitle(title: String): Label?
}