package io.github.tomplum.aoc.train

import io.github.tomplum.aoc.extensions.product

class TicketScanner(private val document: TrainServiceDocument) {

    fun getErrorRate(): Int {
        return document.nearbyTickets.flatMap { it.fieldValues }.sumBy { value ->
            if (document.rules.all { value !in it.lowerBound && value !in it.upperBound }) value else 0
        }
    }

    fun scan(field: String): Long {
        val valid = document.nearbyTickets.filter { ticket -> ticket.isValid(document.rules) }
        val candidate = valid.first()
        val ruleIndices = document.rules.map { rule ->
            val matching = candidate.fieldValues.find { value -> value in rule.lowerBound || value in rule.upperBound }!!
            candidate.fieldValues.indexOf(matching) to rule.name
        }

        val ruleCandidates = document.rules.toMutableList()

        val ruleMap = (valid.first().fieldValues.indices).map { i ->
            val values = valid.map { it.fieldValues[i] }
            val rules = ruleCandidates.filter { rule -> values.all { rule.apply(it) } }
            i to rules
        }.toMap().toMutableMap()

        val sorted = mutableMapOf<Int, TicketRule>()

        while(ruleCandidates.size > 0) {
            val match = ruleMap.entries.find { it.value.size == 1 }!!
            val rule = match.value.first()
            ruleMap.forEach { (i, rules) ->
                if (rules.contains(rule)) {
                    val updated = rules.toMutableList() - rule
                    ruleMap[i] = updated
                }
            }
            ruleCandidates.remove(rule)
            sorted[match.key] = rule
        }


        val filteredRules = sorted.filter { it.value.name.contains(field) }.toMap()
        val departureValues = filteredRules.keys.map { i -> document.ticket.fieldValues[i] }
        return departureValues.map { it.toLong() }.product()
    }
}