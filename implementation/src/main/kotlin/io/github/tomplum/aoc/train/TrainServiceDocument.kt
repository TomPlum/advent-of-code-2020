package io.github.tomplum.aoc.train

data class TrainServiceDocument(val ticket: EncodedTicket, val nearbyTickets: List<EncodedTicket>, val rules : List<TicketRule>)