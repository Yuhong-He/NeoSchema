package org.yunnanhistory.neoschema.utils

import org.springframework.stereotype.Service
import org.yunnanhistory.neoschema.domain.sql.Label
import org.yunnanhistory.neoschema.exceptions.ResourceExistsException
import org.yunnanhistory.neoschema.exceptions.ResourceNotFoundException
import org.yunnanhistory.neoschema.repository.LabelRepository

@Service
class LabelUtils(private val labelRepository: LabelRepository) {
    fun validateLabelIdExists(id: Long) {
        labelRepository.findById(id).orElse(null) ?: throw ResourceNotFoundException(Label::class, "id=${id}")
    }

    fun validateLabelTitleNotExists(title: String, exceptItself: Boolean = false, id: Long? = null) {
        labelRepository.findByTitle(title)?.let { label ->
            if (!(exceptItself && label.id == id)) {
                throw ResourceExistsException(Label::class, "title=$title")
            }
        }
    }
}