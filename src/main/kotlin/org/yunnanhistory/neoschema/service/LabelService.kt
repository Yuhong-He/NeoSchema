package org.yunnanhistory.neoschema.service

import org.springframework.dao.EmptyResultDataAccessException
import org.springframework.stereotype.Service
import org.yunnanhistory.neoschema.domain.sql.entity.Label
import org.yunnanhistory.neoschema.repository.LabelRepository
import java.util.*

@Service
class LabelService(
    private val labelRepository: LabelRepository
) {
    fun getById(id: Long): Label {
        val label: Optional<Label> = labelRepository.findById(id)
        return label.orElseThrow {
            EmptyResultDataAccessException("No label found with id $id", 1)
        }
    }
}