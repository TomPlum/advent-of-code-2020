package io.github.tomplum.aoc.train

import io.github.tomplum.aoc.extensions.product

class TicketScanner(private val document: TrainServiceDocument) {

    fun getErrorRate(): Int {
        return document.nearbyTickets.flatMap { it.fieldValues }.sumBy { value ->
            if (document.rules.all { !it.apply(value) }) value else 0
        }
    }

    fun scan(field: String): Long {
        val valid = document.nearbyTickets.filter { ticket -> ticket.isValid(document.rules) }

        val ruleCandidates = document.rules.toMutableList()

        val columnRuleMap = (valid.first().fieldValues.indices).map { i ->
            val values = valid.map { it.fieldValues[i] }
            val rules = ruleCandidates.filter { rule -> values.all { rule.apply(it) } }
            i to rules
        }.toMap().toMutableMap()

        val sorted = mutableMapOf<Int, TicketRule>()

        while(ruleCandidates.size > 0) {
            val (column, matchedRules) = columnRuleMap.entries.find { it.value.size == 1 }!!
            val matched = matchedRules.first()
            columnRuleMap.forEach { (i, rules) ->
                if (rules.contains(matched)) {
                    val updated = rules.toMutableList() - matched
                    columnRuleMap[i] = updated
                }
            }
            ruleCandidates.remove(matched)
            sorted[column] = matched
        }

        val matchedColumns = sorted.filterValues { rule -> rule.name.contains(field) }.keys
        val matchedFieldValues = matchedColumns.map { i -> document.ticket.fieldValues[i] }
        return matchedFieldValues.map { it.toLong() }.product()
    }
}