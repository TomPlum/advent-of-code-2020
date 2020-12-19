package io.github.tomplum.aoc.forest.satellite

data class Message(val value: String) {
    fun firstCharacter(): Char = value.first()

    fun isEmpty(): Boolean = value.isEmpty()

    fun dropFirst() = Message(value.drop(1))
}