package io.github.tomplum.aoc.forest.satellite

data class Message(val value: String) {
    fun takeFirst() = Message(value.take(1))

    fun takeFirstTwo() = Message(value.take(2))

    fun firstCharacter(): Char = value.first()

    fun isEmpty(): Boolean = value.isEmpty()

    fun dropFirst() = Message(value.drop(1))

    fun takeFor(rule: MessageRule): Message = when(rule) {
        is BaseRule -> takeFirst()
        is OrRule -> takeFirstTwo()
        else -> throw IllegalArgumentException("Invalid Rule Type: ${rule.javaClass.simpleName}")
    }
}