package br.com.expense.repository

import br.com.expense.entity.Spent
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import java.time.LocalDateTime

interface SpentRepository : CrudRepository<Spent, Long> {

    @Query("SELECT spent FROM Spent spent WHERE spent.dateTime BETWEEN :startDateTime AND :endDateTime")
    fun findByPeriod(startDateTime: LocalDateTime, endDateTime: LocalDateTime): List<Spent>
}