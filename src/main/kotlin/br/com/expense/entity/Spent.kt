package br.com.expense.entity

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "spent")
data class Spent (
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) val id: Long = 0,
    val personName: String,
    val description: String,
    val dateTime: LocalDateTime,
    val amount: Double,
    val tags: String
)