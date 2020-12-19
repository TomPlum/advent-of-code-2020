package io.github.tomplum.aoc.forest.satellite.rule

import io.github.tomplum.aoc.forest.satellite.Message
import io.github.tomplum.aoc.forest.satellite.MessageReport

/**
 * A rule governing that a given [Message] must match its first character to the given [value].
 * @see MessageReport
 * @param value The character which must match the messages first character.
 */
data class MatchRule(val value: Char): MessageRule {
    /**
     * Checks to see if the first character of the given [message] matches the rules [value].
     * @param message The message to validate.
     * @return true if matches, else false.
     */
    fun isMatch(message: Message): Boolean = message.firstCharacter() == value
}