package io.github.tomplum.aoc.forest.satellite

abstract class MessageRule(open val number: Int) {
    abstract fun matches(message: Message): Boolean
}