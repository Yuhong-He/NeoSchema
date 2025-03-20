package org.yunnanhistory.neoschema.service

import org.springframework.stereotype.Service
import org.yunnanhistory.neoschema.domain.sql.Label
import org.yunnanhistory.neoschema.domain.sql.LabelCreateRequestDTO
import org.yunnanhistory.neoschema.domain.sql.LabelUpdateRequestDTO
import org.yunnanhistory.neoschema.repository.LabelRepository
import org.yunnanhistory.neoschema.utils.LabelUtils

@Service
class LabelService(
    private val labelRepository: LabelRepository,
    private val labelUtils: LabelUtils
) {
    fun getById(id: Long): Label {
        labelUtils.validateLabelIdExists(id)
        return labelRepository.findById(id).get()
    }

    fun create(dto: LabelCreateRequestDTO): Label {
        labelUtils.validateLabelTypeAndTitleNotExists(dto.type, dto.title)
        val label = Label.fromDTO(dto)
        label.validateTypeMatchNeo4jNamingRule()
        return labelRepository.save(label)
    }

    fun update(dto: LabelUpdateRequestDTO): Label {
        labelUtils.validateLabelIdExists(dto.id)
        labelUtils.validateLabelTypeAndTitleNotExists(dto.type, dto.title, true, dto.id)
        val label = Label.fromDTO(dto)
        label.validateTypeMatchNeo4jNamingRule()
        return labelRepository.save(label)
    }

    fun delete(id: Long) {
        labelUtils.validateLabelIdExists(id)
        labelRepository.deleteById(id)
    }
}