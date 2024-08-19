package br.com.expense.service

import br.com.expense.dto.*
import br.com.expense.repository.SpentRepository
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException
import java.time.LocalDateTime

@Service
class SpentService (private val repository: SpentRepository) {

    fun getExpense(): List<SpentResponse> {
        val expense = repository.findAll().toList()
        return expense.map { it.toResponse() }
    }

    fun getSpent(id: Long): SpentResponse {
        val spent = repository.findById(id)
        val response = spent.map { it.toResponse() }

        return response.orElseThrow { ResponseStatusException(HttpStatus.NOT_FOUND) }
    }

    fun createSpent(request: SpentCreateRequest): SpentResponse {
        val spent = request.toEntity()
        val spentCreated = repository.save(spent)

        return spentCreated.toResponse()
    }

    fun updateSpent(id: Long, request: SpentUpdateRequest): SpentResponse {
        existsSpent(id)

        val spent = request.toEntity(id)
        val spentUpdated = repository.save(spent)

        return spentUpdated.toResponse()
    }

    fun deleteSpent(id: Long) {
        existsSpent(id)
        repository.deleteById(id)
    }

    fun getReport(startDateTime: LocalDateTime, endDateTime: LocalDateTime): List<SpentReportResponse> {
        val expense = repository.findByPeriod(startDateTime, endDateTime)

        if (expense.isEmpty()) return emptyList()

        val days = expense.map { it.dateTime }.distinct()
        val reports = ArrayList<SpentReportResponse>()

        days.forEach{
            day ->
            run {
                val expenseFiltered = expense.filter { spent -> spent.dateTime.isEqual(day) }
                val averageSpent = expenseFiltered.sumOf { it.amount } / expenseFiltered.size

                reports.add(SpentReportResponse(day, averageSpent))
            }
        }

        val highestSpentDay = highestSpentDay(reports)

        reports.forEach { report -> if (report.dateTime.isEqual(highestSpentDay)) report.highestSpentDay = true }

        return reports
    }

    private fun existsSpent(id: Long) {
        if (!repository.existsById(id)) throw ResponseStatusException(HttpStatus.NOT_FOUND)
    }

    private fun highestSpentDay(reports: List<SpentReportResponse>): LocalDateTime {
        val response = reports.maxBy { report -> report.averageSpent }
        return response.dateTime
    }
}