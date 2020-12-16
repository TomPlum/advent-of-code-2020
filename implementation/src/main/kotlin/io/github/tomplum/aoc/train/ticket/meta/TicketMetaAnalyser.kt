package io.github.tomplum.aoc.train.ticket.meta

import io.github.tomplum.aoc.train.ticket.EncodedTicket
import io.github.tomplum.aoc.train.ticket.TicketRule

/**
 * Analyses a collection of [EncodedTicket]s to discover the [TicketMeta] that correctly maps all columns
 * to the respective field names.
 * @param rules The ticket rules
 */
class TicketMetaAnalyser(private val rules: List<TicketRule>) {
    /**
     * Analyses the given [tickets] and finds the column -> field name relationships.
     * @param tickets The tickets to be analysed.
     * @return The encoded ticket meta-data.
     */
    fun analyse(tickets: List<EncodedTicket>): TicketMeta {
        val ruleCandidates = rules.toMutableList()

        val columnRuleMap = (tickets.first().fieldValues.indices).map { column ->
            val values = tickets.map { ticket -> ticket.fieldValues[column] }
            val matchingRules = ruleCandidates.filter { rule -> values.all { value -> rule.apply(value) } }
            column to matchingRules
        }.toMap().toMutableMap()

        val meta = TicketMeta()

        while (ruleCandidates.size > 0) {
            val (column, matchedRules) = columnRuleMap.entries.find { it.value.size == 1 }!!
            val matched = matchedRules.first()
            columnRuleMap.forEach { (column, rules) ->
                if (rules.contains(matched)) {
                    columnRuleMap[column] = rules.toMutableList() - matched
                }
            }
            ruleCandidates.remove(matched)
            meta.addRule(column, matched)
        }

        return meta
    }
}