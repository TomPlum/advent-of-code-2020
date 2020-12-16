package io.github.tomplum.aoc.train.ticket.meta

import io.github.tomplum.aoc.train.ticket.TicketRule

class TicketMeta {
    private val values = mutableMapOf<Int, TicketRule>()

    fun add(column: Int, rule: TicketRule) {
        values[column] = rule
    }

    fun get(column: Int): TicketRule = values[column] ?: throw IllegalArgumentException("No rule defined for column: $column")
}