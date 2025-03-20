package org.yunnanhistory.neoschema.service

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.yunnanhistory.neoschema.domain.sql.Label
import org.yunnanhistory.neoschema.domain.sql.Property
import org.yunnanhistory.neoschema.domain.sql.PropertyResponseDTOList
import org.yunnanhistory.neoschema.exceptions.ClientErrorException
import org.yunnanhistory.neoschema.exceptions.ResourceNotFoundException
import org.yunnanhistory.neoschema.repository.LabelRepository
import org.yunnanhistory.neoschema.repository.PropertyRepository
import org.yunnanhistory.neoschema.utils.LabelUtils

@Service
class PropertyService(
    private val propertyRepository: PropertyRepository,
    private val labelRepository: LabelRepository,
    private val labelUtils: LabelUtils
) {
    fun getByLabelIdAndDisplayOrder(labelId: Long, displayOrder: Int): Property {
        val property = propertyRepository.findByLabelIdAndDisplayOrder(labelId, displayOrder)
        return property ?: throw ResourceNotFoundException(Property::class, "labelId=$labelId;displayOrder=$displayOrder")
    }

    @Transactional
    fun createOrUpdate(dtoList: PropertyResponseDTOList): Label {

        val labelId = dtoList.labelId

        // Validate label exists
        labelUtils.validateLabelIdExists(labelId)

        // Delete all properties of the label
        propertyRepository.deleteAllByLabelId(labelId)

        // Convert DTOs to Property entities
        val properties = dtoList.properties.map { dto ->
            Property.fromDTO(labelId, dto)
        }

        // Validate properties and check regex
        properties.forEach { property ->
            if (property.regex != null && !property.validateRegex()) {
                throw ClientErrorException(message = "Invalid regex: ${property.regex}")
            }
        }

        // Save all properties in batch
        propertyRepository.saveAll(properties)

        // Return updated Label
        return labelRepository.findById(labelId).get()
    }
}