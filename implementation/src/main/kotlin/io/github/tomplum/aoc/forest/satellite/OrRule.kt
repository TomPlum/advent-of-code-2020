package io.github.tomplum.aoc.forest.satellite

data class OrRule(override val number: Int, val first: AndRule, val second: AndRule): MessageRule(number) {
    override fun matches(message: Message): Boolean {
       /* val firstResponse = first.matches(message.takeFirstTwo())
        val secondResponse = second.matches(message.takeFirstTwo())
        return RuleResponse(secondResponse.message, firstResponse.isMatch || secondResponse.isMatch)*/
        return first.matches(message) || second.matches(message)
    }
}