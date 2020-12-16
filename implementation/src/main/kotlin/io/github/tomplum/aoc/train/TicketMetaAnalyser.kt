package io.github.tomplum.aoc.train

class TicketMetaAnalyser(private val rules: List<TicketRule>) {
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
            meta.add(column, matched)
        }

        return meta
    }
}