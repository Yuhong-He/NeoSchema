package org.yunnanhistory.neoschema.domain.sql

import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.IdClass
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table
import lombok.Data
import lombok.ToString
import org.yunnanhistory.neoschema.exceptions.ClientErrorException
import java.io.Serializable
import java.util.regex.PatternSyntaxException

@Entity
@Table(name = "property")
@Data
@ToString
@IdClass(PropertyId::class)
class Property (
    @Id
    @Column(name = "label_id", nullable = false)
    val labelId: Long? = null,

    @Id
    @Column(name = "display_order", nullable = false)
    val displayOrder: Int = 0,

    @Column(nullable = false, length = 255)
    val type: String = "",

    @Column(nullable = false, length = 255)
    val title: String = "",

    @Column(nullable = true, length = 255)
    val alias: String? = null,

    val isNumeric: Boolean = false,

    val isUrl: Boolean = false,

    @Column(nullable = true, length = 255)
    val regex: String? = null,

    @ManyToOne
    @JoinColumn(name = "label_id", insertable = false, updatable = false)
    @JsonIgnore
    var label: Label? = null
) {
    companion object {
        fun fromDTO(labelId: Long, dto: PropertyRequestDTO) = Property(
            labelId = labelId,
            displayOrder = dto.displayOrder,
            type = dto.type,
            title = dto.title,
            alias = dto.alias,
            isNumeric = dto.isNumeric,
            isUrl = dto.isUrl,
            regex = dto.regex
        )
    }

    fun validateRegex() {
        if (regex == null) return
        try {
            regex?.let { Regex(it) }
        } catch (e: PatternSyntaxException) {
            throw ClientErrorException(message = "Invalid regex: $regex")
        }
    }

    fun validateTypeMatchNeo4jNamingRule() {
        val regex = "^[a-z][A-Za-z0-9_]*$".toRegex()
        if (!regex.matches(type)) {
            throw ClientErrorException(message = "Property type name: '$type', must match Neo4j naming rule: ^[a-z][A-Za-z0-9_]*$")
        }
    }
}

data class PropertyId(
    val labelId: Long = 0,
    val displayOrder: Int = 0
) : Serializable

class PropertyRequestDTO(
    val displayOrder: Int,
    val type: String,
    val title: String,
    val alias: String?,
    val isNumeric: Boolean,
    val isUrl: Boolean,
    val regex: String?
)

class PropertyResponseDTOList(
    val labelId: Long,
    val properties: List<PropertyRequestDTO>,
)
