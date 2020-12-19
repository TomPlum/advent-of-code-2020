package io.github.tomplum.aoc.forest.satellite

data class LinearRule(override val number: Int, val rules: List<MessageRule>): MessageRule(number) {
    override fun matches(message: Message): Boolean {
        return false
    }
}