package org.yunnanhistory.neoschema.service

import org.springframework.stereotype.Service
import org.yunnanhistory.neoschema.domain.sql.Label
import org.yunnanhistory.neoschema.domain.sql.LabelRequestDTO
import org.yunnanhistory.neoschema.exceptions.ResourceExistsException
import org.yunnanhistory.neoschema.exceptions.ResourceNotFoundException
import org.yunnanhistory.neoschema.repository.LabelRepository

@Service
class LabelService(
    private val labelRepository: LabelRepository
) {
    fun getById(id: Long): Label {
        return labelRepository.findById(id).orElse(null) ?: throw ResourceNotFoundException(Label::class, "id=${id}")
    }

    fun create(dto: LabelRequestDTO): Label {
        val label = labelRepository.findByTitle(dto.title)
        if (label != null) {
            throw ResourceExistsException(Label::class, "title=${dto.title}")
        }
        return labelRepository.save(Label.fromDTO(dto))
    }
}