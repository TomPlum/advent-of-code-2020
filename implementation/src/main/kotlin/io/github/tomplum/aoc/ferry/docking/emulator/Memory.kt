package io.github.tomplum.aoc.ferry.docking.emulator

import io.github.tomplum.aoc.ferry.docking.strategy.DecodingStrategy

/**
 * A memory module used by the [DecoderEmulator] to store 36-bit unsigned integers.
 * @see DecodingStrategy
 */
class Memory {
    private val addresses = mutableMapOf<Long, Long>()

    /**
     * Adds a single [value] into memory at the given [index].
     * @param index The index to store the value at.
     * @param value The value to store in memory.
     */
    fun add(index: Long, value: Long) {
        addresses[index] = value
    }

    /**
     * Calculates the sum of all the values currently stored in memory.
     * @return The sum of all values.
     */
    fun sum(): Long = addresses.values.sum()
}