package io.github.tomplum.aoc.forest.satellite

data class MessageReport(val rules: MutableMap<Int, MessageRule>, val messages: List<Message>) {
    fun getMessagesMatchingRule(id: Int): Int {
        return messages.count { message -> isMatch(rules, message, listOf(id)) }
    }

    fun replaceRules(rules: Map<Int, MessageRule>) = rules.forEach { (id, rule) -> this.rules[id] = rule }

    private fun isMatch(rules: Map<Int, MessageRule>, message: Message, currentRules: List<Int>) : Boolean {
        if (message.isEmpty()) {
            return currentRules.isEmpty()
        } else if (currentRules.isEmpty()) {
            return false
        }
        return when(val rule = rules[currentRules.first()]) {
            is LinearRule -> isMatch(rules, message, rule.ids + currentRules.drop(1))
            is OrRule -> rule.ids.any { ids -> isMatch(rules, message, ids + currentRules.drop(1)) }
            is MatchRule -> if (message.firstCharacter() == rule.value) {
                isMatch(rules, message.dropFirst(), currentRules.drop(1))
            } else false
            else -> throw IllegalArgumentException("Invalid Rule Type: ${rule?.javaClass?.simpleName}")
        }
    }
}