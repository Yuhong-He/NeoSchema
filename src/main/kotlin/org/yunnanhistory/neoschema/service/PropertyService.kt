package org.yunnanhistory.neoschema.service

import org.springframework.dao.EmptyResultDataAccessException
import org.springframework.stereotype.Service
import org.yunnanhistory.neoschema.domain.sql.entity.Property
import org.yunnanhistory.neoschema.repository.PropertyRepository
import java.util.*

@Service
class PropertyService(
    private val propertyRepository: PropertyRepository
) {
    fun getByLabelIdAndDisplayOrder(labelId: Long, displayOrder: Int): Property {
        val property: Optional<Property> = propertyRepository.findByLabelIdAndDisplayOrder(labelId, displayOrder)
        return property.orElseThrow {
            EmptyResultDataAccessException("No property found with labelId $labelId and displayOrder $displayOrder", 1)
        }
    }
}