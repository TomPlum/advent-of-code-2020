package io.github.tomplum.aoc.train

data class TrainServiceDocument(val ticket: Ticket, val nearbyTickets: List<Ticket>, val rules : List<TicketRule>)