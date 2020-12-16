package io.github.tomplum.aoc.train

data class Ticket(val fieldValues: List<Int>) {
    fun isValid(rules: List<TicketRule>): Boolean = fieldValues.all { value ->
        rules.any { rule -> value in rule.lowerBound || value in rule.upperBound }
    }
}