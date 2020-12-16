package io.github.tomplum.aoc.train

import io.github.tomplum.aoc.extensions.product

class TicketScanner(private val document: TrainServiceDocument) {

    fun getErrorRate(): Int = document
        .getAllTicketValues()
        .sumBy { value -> if (!document.satisfiesAllRules(value)) value else 0 }

    fun scan(field: String): Long {
        val validTickets = document.getValidTickets()
        val meta = TicketMetaAnalyser(document.rules).analyse(validTickets)
        return document.ticket.decode(meta).getFields(field).map { it.toLong() }.product()
    }
}