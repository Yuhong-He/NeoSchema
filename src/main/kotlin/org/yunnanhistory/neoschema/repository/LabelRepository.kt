package org.yunnanhistory.neoschema.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import org.yunnanhistory.neoschema.domain.sql.entity.Label

@Repository
interface LabelRepository: JpaRepository<Label, Long> {
    fun findByTitle(title: String): Label?
}