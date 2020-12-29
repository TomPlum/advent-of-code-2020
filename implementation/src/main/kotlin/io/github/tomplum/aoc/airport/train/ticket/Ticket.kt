package io.github.tomplum.aoc.airport.train.ticket

/**
 * A single ticket granting access to board the train.
 * It has been decoded and can be read in plain English.
 * @see EncodedTicket
 * @param values A map of fields and their respective values.
 */
data class Ticket(private val values: Map<String, Int>) {
    /**
     * Finds all the fields whose name contains the given [term].
     * @return A collection of all the values whose field names were matched.
     */
    fun getFields(term: String): Collection<Int> = values.filterKeys { column -> column.contains(term) }.values
}