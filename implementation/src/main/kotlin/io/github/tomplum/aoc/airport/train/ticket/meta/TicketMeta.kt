package io.github.tomplum.aoc.airport.train.ticket.meta

import io.github.tomplum.aoc.airport.train.ticket.EncodedTicket
import io.github.tomplum.aoc.airport.train.ticket.TicketRule

/**
 * A map of column indices and ticket rules used to decode an [EncodedTicket].
 * @param values The stored column -> rule mappings.
 */
data class TicketMeta(private val values: MutableMap<Int, TicketRule> = mutableMapOf()) {

    /**
     * Adds a new rule to the meta.
     * @param column The zero-based index of the column.
     * @param rule The ticket rule to map against.
     */
    fun addRule(column: Int, rule: TicketRule) {
        values[column] = rule
    }

    /**
     * Gets the [TicketRule] for the given [column].
     * @throws IllegalArgumentException if there is no mapping for the given [column].
     * @return The corresponding rule.
     */
    fun get(column: Int): TicketRule {
        return values[column] ?: throw IllegalArgumentException("No rule defined for column: $column")
    }
}