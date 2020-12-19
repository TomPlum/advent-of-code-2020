package io.github.tomplum.aoc.forest.satellite

class MessageReader private constructor() {
    companion object {
        fun parse(data: String): MessageReport {
            val info = data.split("\n\n")
            val ruleInfo = info[0]
            val receivedMessageInfo = info[1]

            val rules = mutableMapOf<Int, MessageRule>()

            val ruleTree = ruleInfo.split("\n").reversed().map { line ->
                val number = line.take(1).toInt()
                val rule = line.drop(3)
                val created = when {
                    rule.contains("|") -> {
                        val ids = rule.filter { it.isDigit() }.map { it.toString().toInt() }
                        OrRule(number, AndRule(rules[ids[0]]!!, rules[ids[1]]!!), AndRule(rules[ids[2]]!!, rules[ids[3]]!!))
                    }
                    rule.contains("\"") -> {
                        BaseRule(number, rule.removeSurrounding("\"").first())
                    }
                    else -> {
                        LinearRule(number, rule.replace(" ", "").map { rules[it.toString().toInt()]!! })
                    }
                }
                rules[number] = created
                created
            }

            val receivedMessages = receivedMessageInfo.split("\n").map { Message(it.trim()) }

            return MessageReport(ruleTree, receivedMessages)
        }
    }
}