package io.github.tomplum.aoc.train.ticket

import io.github.tomplum.aoc.train.ticket.meta.TicketMeta

/**
 * A single ticket granting access to board the train.
 * It is written in a language that you cannot understand.
 * @param fieldValues A list of the numerical field values on the ticket.
 */
data class EncodedTicket(val fieldValues: List<Int>) {
    /**
     * An encoded ticket is deemed valid if all of its [fieldValues] match any of the given [rules].
     * @return True if all fields are valid, else false.
     */
    fun isValid(rules: List<TicketRule>): Boolean = fieldValues.all { value ->
        rules.any { rule -> rule.apply(value) }
    }

    /**
     * Decodes the ticket using the given [meta].
     * The [fieldValues] are assigned to their correct field names, producing a readable [Ticket].
     * @return The decoded ticket.
     */
    fun decode(meta: TicketMeta): Ticket = fieldValues
        .mapIndexed { i, value -> meta.get(i).name to value }
        .let { Ticket(it.toMap()) }

}