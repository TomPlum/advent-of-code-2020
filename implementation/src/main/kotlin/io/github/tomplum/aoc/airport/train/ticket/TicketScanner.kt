package io.github.tomplum.aoc.airport.train.ticket

import io.github.tomplum.aoc.airport.train.ticket.meta.TicketMeta
import io.github.tomplum.aoc.airport.train.ticket.meta.TicketMetaAnalyser
import io.github.tomplum.aoc.extensions.product

/**
 * Scans the tickets in the given [document].
 */
class TicketScanner(private val document: TrainServiceDocument) {
    /**
     * Scans all the tickets in the [document] and compiles a list of all the invalid field values.
     * @return The sum of all the invalid field values.
     */
    fun getErrorRate(): Int = document.getInvalidTicketValues().sum()

    /**
     * Scans the [document] and filters out the invalid tickets.
     * Any remaining valid tickets are passed through the [TicketMetaAnalyser] to match the names of the fields
     * to their corresponding columns. The [TicketMeta] can then be used to decode our ticket and understand the
     * meaning of the values. Finally, the field names containing the given [field] are filtered out.
     *
     * @param field A field name sub-string to match and filter against.
     * @return The product of the filtered field values.
     */
    fun scan(field: String): Long {
        val validTickets = document.getValidTickets()
        val meta = TicketMetaAnalyser(document.rules).analyse(validTickets)
        return document.ticket.decode(meta).getFields(field).map { it.toLong() }.product()
    }
}