package io.github.tomplum.aoc.ferry.docking

class Memory {
    private val addresses = mutableMapOf<Long, Long>()

    fun add(index: Long, value: Long) {
        addresses[index] = value
    }

    fun sum(): Long = addresses.values.sum()
}