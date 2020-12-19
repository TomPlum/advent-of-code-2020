package io.github.tomplum.aoc.forest.satellite

class MessageReader private constructor() {
    companion object {
        fun parse(data: String): MessageReport {
            val info = data.split("\n\n")
            val ruleInfo = info[0]
            val receivedMessageInfo = info[1]

            val rules = ruleInfo.split("\n").map { line ->
                val number = line.split(":")[0].toInt()
                val rule = line.split(":")[1].trim()
                when {
                    rule.contains("|") -> {
                        val ids = rule.split("|").map { it.trim().split(" ").map { it.toInt() } }
                        number to OrRule(ids)
                    }
                    rule.contains("\"") -> number to MatchRule(rule.removeSurrounding("\"").first())
                    else -> number to LinearRule(rule.split(" ").map { it.toInt() })
                }
            }.toMap().toMutableMap()

            val receivedMessages = receivedMessageInfo.split("\n").map { Message(it.trim()) }

            return MessageReport(rules, receivedMessages)
        }
    }
}