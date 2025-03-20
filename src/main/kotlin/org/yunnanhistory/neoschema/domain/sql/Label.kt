package org.yunnanhistory.neoschema.domain.sql

import jakarta.persistence.CascadeType
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.OneToMany
import jakarta.persistence.Table
import lombok.Data


@Entity
@Table(name = "label")
@Data
class Label(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(nullable = false, length = 255)
    val title: String = "",

    @Column(nullable = true, length = 255)
    val alias: String? = null,

    val categorisable: Boolean = false,

    @OneToMany(mappedBy = "label", cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    val properties: List<Property> = mutableListOf()
) {
    companion object {
        fun fromDTO(dto: LabelRequestDTO) = Label(
            title = dto.title,
            alias = dto.alias,
            categorisable = dto.categorisable
        )
    }
}

class LabelRequestDTO(val title: String, val alias: String?, val categorisable: Boolean)