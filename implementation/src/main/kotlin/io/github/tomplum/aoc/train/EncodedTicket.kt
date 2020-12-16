package io.github.tomplum.aoc.train

data class EncodedTicket(val fieldValues: List<Int>) {
    fun isValid(rules: List<TicketRule>): Boolean = fieldValues.all { value ->
        rules.any { rule -> rule.apply(value) }
    }

    fun decode(meta: TicketMeta): Ticket = fieldValues
        .mapIndexed { i, value -> meta.get(i).name to value }
        .let { Ticket(it.toMap()) }

}