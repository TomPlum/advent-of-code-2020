package io.github.tomplum.aoc.forest.satellite

data class MessageReport(val rules: List<MessageRule>, val messages: List<Message>) {
    fun getMessagesMatchingRule(number: Int): Int {
        val rule = rules.find { it.number == number }!!
        return messages.count { message -> isMatch(listOf(rule), message) }
    }

    private fun isMatch(rules: List<MessageRule>, message: Message) : Boolean {
        if (message.isEmpty()) {
            return rules.isEmpty()
        } else if (rules.isEmpty()) {
            return false
        }
        return when(val rule = rules.first()) {
            is LinearRule -> rule.rules.all { isMatch(listOf(it), message) }
            is OrRule -> listOf(rule.first, rule.second).any { isMatch(listOf(it), message) }
            is AndRule -> listOf(rule.first, rule.second).all { isMatch(listOf(it), message) }
            is BaseRule -> return if (rule.matches(message.takeFirst())) isMatch(rules.drop(1), message.dropFirst()) else false
            else -> throw IllegalArgumentException("Invalid Rule Type: ${rule.javaClass.simpleName}")
        }
    }
}