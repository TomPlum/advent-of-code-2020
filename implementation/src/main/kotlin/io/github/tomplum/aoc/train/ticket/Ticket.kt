package io.github.tomplum.aoc.train.ticket

data class Ticket(val values: Map<String, Int>) {
    fun getFields(term: String): Collection<Int> = values.filterKeys { column -> column.contains(term) }.values
}