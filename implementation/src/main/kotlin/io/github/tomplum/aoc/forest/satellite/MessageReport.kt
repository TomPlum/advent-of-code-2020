package io.github.tomplum.aoc.forest.satellite

/**
 * A [MessageReport] from the Elves at the Mythical Information Bureau.
 *
 * They think their satellite has collected an image of a sea monster, but the satellite connection is having
 * problems, and many of the messages sent back from the satellite have been corrupted.
 *
 * This report contains a list of [rules] that the received satellite [messages] should obey.
 *
 * @param rules A map of message rules and their respective unique IDs.
 * @param messages A list of messages received from the satellite. Some are corrupt.
 */
data class MessageReport(val rules: MutableMap<Int, MessageRule>, val messages: List<Message>) {
    /**
     * Validates all the [messages] according to the rule with the given [id].
     * @param id The id of the [AndRule] of which to verify the [messages] by.
     * @return The number of messages that completely matched the rules.
     */
    fun getMessagesMatchingRule(id: Int): Int = messages.count { message -> isMatch(message, listOf(id)) }

    /**
     * Replaces the given [new] rules in the existing [rules].
     * @param new A map of replacement rules with the IDs of the rules to replace.
     */
    fun replaceRules(new: Map<Int, MessageRule>) = new.forEach { (id, rule) -> this.rules[id] = rule }

    /**
     * Checks to the see if the given [message] matches the given [rules].
     * @param message The current un-checked part of the message.
     * @param currentRules A list of the rule IDs to check against.
     */
    private fun isMatch(message: Message, currentRules: List<Int>) : Boolean {
        if (message.isEmpty()) return currentRules.isEmpty() else if (currentRules.isEmpty()) return false

        return when(val rule = rules[currentRules.first()]) {
            is AndRule -> isMatch(message, rule.ids + currentRules.drop(1))
            is OrRule -> rule.ids.any { ids -> isMatch(message, ids + currentRules.drop(1)) }
            is MatchRule -> if (rule.isMatch(message)) isMatch(message.dropFirst(), currentRules.drop(1)) else false
            else -> throw IllegalArgumentException("Invalid Rule Type: ${rule?.javaClass?.simpleName}")
        }
    }
}