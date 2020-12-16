package io.github.tomplum.aoc.train

import io.github.tomplum.aoc.train.ticket.EncodedTicket
import io.github.tomplum.aoc.train.ticket.TicketRule

data class TrainServiceDocument(val ticket: EncodedTicket, val nearbyTickets: List<EncodedTicket>, val rules : List<TicketRule>) {
    fun getAllTicketValues(): List<Int> = nearbyTickets.flatMap { it.fieldValues }

    fun satisfiesAllRules(value: Int): Boolean = rules.all { rule -> !rule.apply(value) }

    fun getValidTickets(): List<EncodedTicket> = nearbyTickets.filter { ticket -> ticket.isValid(rules) }
}