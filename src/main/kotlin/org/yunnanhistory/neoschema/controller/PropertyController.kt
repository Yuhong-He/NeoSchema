package org.yunnanhistory.neoschema.controller

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.yunnanhistory.neoschema.domain.sql.entity.Property
import org.yunnanhistory.neoschema.service.PropertyService

@RestController
@RequestMapping("/api/v1/schema/property/")
class PropertyController(
    private val propertyService: PropertyService
) {
    @GetMapping
    fun getPropertyById(
        @RequestParam("labelId") labelId: Long,
        @RequestParam("displayOrder") displayOrder: Int
    ): ResponseEntity<Property> {
        return ResponseEntity.ok(propertyService.getByLabelIdAndDisplayOrder(labelId, displayOrder))
    }
}