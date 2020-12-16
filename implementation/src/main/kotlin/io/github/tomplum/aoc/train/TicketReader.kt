package io.github.tomplum.aoc.train

class TicketReader private constructor() {
    companion object {
        fun read(notes: String): TrainServiceDocument {
            val info = notes.split("\n\n")

            val rules = info[0].split("\n").map { rule ->
                val ruleData = rule.split(": ")
                val name = ruleData[0]
                val bounds = ruleData[1].split(" or ")
                val lower = bounds[0].getBound()
                val upper = bounds[1].getBound()
                TicketRule(name, lower, upper)
            }

            val ticket = info[1].split("\n").last().split(",").map { it.toInt() }.let { EncodedTicket(it) }

            val nearbyTickets = info[2].split("\n").drop(1).map { EncodedTicket(it.split(',').map { it.toInt() }) }

            return TrainServiceDocument(ticket, nearbyTickets, rules)
        }

        private fun String.getBound(): IntRange = split("-").map { it.toInt() }.let { it.first()..it.last() }
    }
}