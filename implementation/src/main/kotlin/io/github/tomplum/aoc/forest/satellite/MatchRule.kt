package io.github.tomplum.aoc.forest.satellite

data class MatchRule(val value: Char): MessageRule {
    fun isMatch(message: Message): Boolean = message.firstCharacter() == value
}