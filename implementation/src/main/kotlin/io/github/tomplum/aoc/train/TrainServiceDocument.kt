package io.github.tomplum.aoc.train

import io.github.tomplum.aoc.train.ticket.EncodedTicket
import io.github.tomplum.aoc.train.ticket.TicketRule

data class TrainServiceDocument(val ticket: EncodedTicket, val nearbyTickets: List<EncodedTicket>, val rules : List<TicketRule>) {

    fun getInvalidTicketValues(): List<Int> = getAllTicketValues().filter { value ->
        rules.all { rule -> !rule.apply(value) }
    }

    fun getValidTickets(): List<EncodedTicket> = nearbyTickets.filter { ticket -> ticket.isValid(rules) }

    private fun getAllTicketValues(): List<Int> = nearbyTickets.flatMap { it.fieldValues }
}