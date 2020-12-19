package io.github.tomplum.aoc.forest.satellite

class MessageReader private constructor() {
    companion object {
        fun parse(data: String): MessageReport {
            val info = data.split("\n\n")
            val ruleInfo = info[0]
            val receivedMessageInfo = info[1]

            val rules = ruleInfo.split("\n").map { line ->
                val number = line.take(1).toInt()
                val rule = line.drop(3)
                when {
                    rule.contains("|") -> {
                        val ids = rule.filter { it.isDigit() }.map { it.toString().toInt() }
                        OrRule(number, Pair(ids[0], ids[1]), Pair(ids[2], ids[3]))
                    }
                    rule.contains("\"") -> BaseRule(number, rule.removeSurrounding("\"").toCharArray().first())
                    else -> LinearRule(number, rule.replace(" ", "").map { it.toString().toInt() })
                }
            }

            val receivedMessages = receivedMessageInfo.split("\n").map { Message(it.trim()) }

            return MessageReport(rules, receivedMessages)
        }
    }
}