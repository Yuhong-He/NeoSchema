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
        labelUtils.validateLabelTitleNotExists(dto.title)
        return labelRepository.save(Label.fromDTO(dto))
    }

    fun update(dto: LabelUpdateRequestDTO): Label {
        labelUtils.validateLabelIdExists(dto.id)
        labelUtils.validateLabelTitleNotExists(dto.title, true, dto.id)
        return labelRepository.save(Label.fromDTO(dto))
    }

    fun delete(id: Long) {
        labelUtils.validateLabelIdExists(id)
        labelRepository.deleteById(id)
    }
}