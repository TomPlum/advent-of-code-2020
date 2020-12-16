package io.github.tomplum.aoc.train.ticket.meta

import io.github.tomplum.aoc.train.ticket.TicketRule

data class TicketMeta(private val values: MutableMap<Int, TicketRule> = mutableMapOf()) {

    fun addRule(column: Int, rule: TicketRule) {
        values[column] = rule
    }

    fun get(column: Int): TicketRule {
        return values[column] ?: throw IllegalArgumentException("No rule defined for column: $column")
    }
}