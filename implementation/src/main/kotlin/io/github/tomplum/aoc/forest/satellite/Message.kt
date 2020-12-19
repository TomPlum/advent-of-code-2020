package io.github.tomplum.aoc.forest.satellite

/**
 * A single message returned by the Elves satellite.
 * @param value The contents of the message
 */
data class Message(private val value: String) {
    /**
     * Gets the first character of the message.
     * @return The first character.
     */
    fun firstCharacter(): Char = value.first()

    /**
     * Checks to see if the message [value] is empty.
     * @return true if empty, else false.
     */
    fun isEmpty(): Boolean = value.isEmpty()

    /**
     * Creates a copy of the message with the first [value] character removed.
     * @return The remaining message
     */
    fun dropFirst() = Message(value.drop(1))
}