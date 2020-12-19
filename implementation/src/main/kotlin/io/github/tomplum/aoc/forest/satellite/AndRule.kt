package io.github.tomplum.aoc.forest.satellite

data class AndRule(val first: MessageRule, val second: MessageRule): MessageRule(0) {
    override fun matches(message: Message): Boolean {
       /* val firstMatches = first.matches(message.takeFor(first))
        val secondMatches = second.matches(message.takeFor(second))
        return RuleResponse(secondMatches.message, firstMatches.isMatch && secondMatches.isMatch)*/
        return first.matches(message) && second.matches(message)
    }
}