package org.yunnanhistory.neoschema.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import org.yunnanhistory.neoschema.domain.sql.Property

@Repository
interface PropertyRepository: JpaRepository<Property, Long> {
    fun findByLabelIdAndDisplayOrder(labelId: Long, displayOrder: Int): Property?
    fun deleteAllByLabelId(labelId: Long)
}