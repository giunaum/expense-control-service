package br.com.expense.dto

import br.com.expense.entity.Spent
import java.time.LocalDateTime

data class SpentCreateRequest (
    val personName: String,
    val description: String,
    val dateTime: LocalDateTime,
    val amount: Double,
    val tags: String
)

fun SpentCreateRequest.toEntity(): Spent = Spent(
    personName = this.personName,
    description = this.description,
    dateTime = this.dateTime,
    amount = this.amount,
    tags = this.tags
)

data class SpentUpdateRequest (
    val personName: String,
    val description: String,
    val dateTime: LocalDateTime,
    val amount: Double,
    val tags: String
)

fun SpentUpdateRequest.toEntity(id: Long): Spent = Spent(
    id = id,
    personName = this.personName,
    description = this.description,
    dateTime = this.dateTime,
    amount = this.amount,
    tags = this.tags
)