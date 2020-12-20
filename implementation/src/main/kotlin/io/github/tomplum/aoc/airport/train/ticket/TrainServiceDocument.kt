package io.github.tomplum.aoc.airport.train.ticket

/**
 * A document outlining information collected at the airport while waiting for the connecting flight. Including;
 * @param ticket Our ticket. Encoded such that we cannot read its field values.
 * @param nearbyTickets A collection of other nearby passengers tickets which are also encoded.
 * @param rules The rules for the ticket fields. These apply to both our ticket and the nearby ones.
 */
data class TrainServiceDocument(val ticket: EncodedTicket, val nearbyTickets: List<EncodedTicket>, val rules: List<TicketRule>) {

    /**
     * Gets all the values from the [nearbyTickets] that are invalid as per the [rules].
     * @return A list of all the invalid field values.
     */
    fun getInvalidTicketValues(): List<Int> = getAllTicketValues().filter { value ->
        rules.all { rule -> !rule.apply(value) }
    }

    /**
     * Gets all the tickets that are valid as per the given [rules].
     * @return A list of valid tickets.
     */
    fun getValidTickets(): List<EncodedTicket> = nearbyTickets.filter { ticket -> ticket.isValid(rules) }

    /**
     * Creates a single collection of field values from all the [nearbyTickets].
     * @return A flattened collection of all ticket field values.
     */
    private fun getAllTicketValues(): List<Int> = nearbyTickets.flatMap { it.fieldValues }
}