package io.github.tomplum.aoc.forest.satellite

data class BaseRule(override val number: Int, val character: Char): MessageRule(number) {
    override fun matches(message: Message): Boolean {
       /* val isMatch = message.firstCharacter() == character
        return RuleResponse(message.dropFirst(), isMatch)*/
        return message.firstCharacter() == character
    }
}