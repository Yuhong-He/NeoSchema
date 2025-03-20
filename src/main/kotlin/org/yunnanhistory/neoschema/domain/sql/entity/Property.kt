package org.yunnanhistory.neoschema.domain.sql.entity

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
import java.io.Serializable

@Entity
@Table(name = "property")
@Data
@ToString
@IdClass(PropertyId::class)
class Property {
    @Id
    @Column(name = "label_id", nullable = false)
    val labelId: Long? = null

    @Id
    @Column(name = "display_order", nullable = false)
    val displayOrder: Int = 0

    @Column(nullable = false, length = 255)
    val title: String? = null

    @Column(nullable = true, length = 255)
    val alias: String? = null

    @ManyToOne
    @JoinColumn(name = "label_id", nullable = false)
    @JsonIgnore
    var label: Label? = null

    val isNumeric: Boolean = false

    val isUrl: Boolean = false

    @Column(nullable = true, length = 255)
    val regex: String? = null
}

data class PropertyId(
    val labelId: Long = 0,
    val displayOrder: Int = 0
) : Serializable
