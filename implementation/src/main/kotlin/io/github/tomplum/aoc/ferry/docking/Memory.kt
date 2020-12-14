package io.github.tomplum.aoc.ferry.docking

class Memory {
    private val values = mutableListOf<Long>()

    fun add(index: Int, value: Long) {
        if (index > values.size - 1) {
            values.apply { repeat(index - values.size + 1) { add(0) } }
        }
        values[index] = value
    }

    fun sum(): Long = values.sum()
}