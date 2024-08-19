package br.com.expense.api

import br.com.expense.dto.SpentCreateRequest
import br.com.expense.dto.SpentReportResponse
import br.com.expense.dto.SpentResponse
import br.com.expense.dto.SpentUpdateRequest
import br.com.expense.service.SpentService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.time.LocalDateTime

@RestController
@RequestMapping("/v1/expense")
class SpentController(private val service: SpentService) {

    @GetMapping
    fun getExpense(): ResponseEntity<List<SpentResponse>> = ResponseEntity.ok(service.getExpense())

    @GetMapping("/{id}")
    fun getSpent(@PathVariable id: Long): ResponseEntity<SpentResponse> = ResponseEntity.ok(service.getSpent(id))

    @PostMapping
    fun createSpent(
        @RequestBody request: SpentCreateRequest
    ): ResponseEntity<SpentResponse> = ResponseEntity(service.createSpent(request), HttpStatus.CREATED)

    @PutMapping("/{id}")
    fun updateSpent(
        @PathVariable id: Long,
        @RequestBody request: SpentUpdateRequest
    ) : ResponseEntity<SpentResponse> = ResponseEntity.ok(service.updateSpent(id, request))

    @DeleteMapping("/{id}")
    fun deleteSpent(@PathVariable id: Long): ResponseEntity<SpentResponse> {
        service.deleteSpent(id)
        return ResponseEntity.noContent().build()
    }

    @GetMapping("/report")
    fun getReport(
        @RequestParam startDateTime: LocalDateTime,
        @RequestParam endDateTime: LocalDateTime
    ): ResponseEntity<List<SpentReportResponse>> = ResponseEntity.ok(service.getReport(startDateTime, endDateTime))
}