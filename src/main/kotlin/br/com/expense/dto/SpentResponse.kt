package br.com.expense.dto

import br.com.expense.entity.Spent
import java.time.LocalDateTime

data class SpentResponse (
    val id: Long,
    val personName: String,
    val description: String,
    val dateTime: LocalDateTime,
    val amount: Double,
    val tags: String
)

fun Spent.toResponse(): SpentResponse = SpentResponse(
    id = this.id,
    personName = this.personName,
    description = this.description,
    dateTime = this.dateTime,
    amount = this.amount,
    tags = this.tags
)

data class SpentReportResponse (
    val dateTime: LocalDateTime,
    val averageSpent: Double,
    var highestSpentDay: Boolean
) {
    constructor(dateTime: LocalDateTime, averageSpent: Double): this(dateTime, averageSpent, false)
}