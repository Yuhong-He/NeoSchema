package org.yunnanhistory.neoschema.controller

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.yunnanhistory.neoschema.domain.sql.Label
import org.yunnanhistory.neoschema.domain.sql.LabelCreateRequestDTO
import org.yunnanhistory.neoschema.domain.sql.LabelUpdateRequestDTO
import org.yunnanhistory.neoschema.service.LabelService

@RestController
@RequestMapping("/api/v1/schema/label/")
class LabelController(
    private val labelService: LabelService
) {
    @GetMapping
    fun getLabelById(@RequestParam("id") id: Long): ResponseEntity<Label> {
        return ResponseEntity.ok(labelService.getById(id))
    }

    @PostMapping
    fun createLabel(@RequestBody dto: LabelCreateRequestDTO): ResponseEntity<Label> {
        return ResponseEntity.ok(labelService.create(dto))
    }

    @PutMapping
    fun updateLabel(@RequestBody dto: LabelUpdateRequestDTO): ResponseEntity<Label> {
        return ResponseEntity.ok(labelService.update(dto))
    }

    @DeleteMapping
    fun deleteLabel(@RequestParam("id") id: Long) {
        labelService.delete(id)
    }
}