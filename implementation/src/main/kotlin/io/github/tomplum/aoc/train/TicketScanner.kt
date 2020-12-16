package io.github.tomplum.aoc.train

class TicketScanner {
    fun scan(document: TrainServiceDocument): Int {
        val rules = document.rules
        return document.nearbyTickets.flatMap { it.fieldValue }.sumBy { value ->
            if (rules.all { value !in it.lowerBound && value !in it.upperBound }) value else 0
        }
    }
}