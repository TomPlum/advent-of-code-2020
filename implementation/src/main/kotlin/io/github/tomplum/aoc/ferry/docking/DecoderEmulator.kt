package io.github.tomplum.aoc.ferry.docking

import io.github.tomplum.aoc.extensions.toDecimal

class DecoderEmulator(private val program: InitialisationProgram) {
    private val memory = Memory()

    //High: 9978784019158, Low: 1730299326456
    fun execute(): Long {
        program.instructions.forEach { (mask, instructions) ->
            instructions.forEach { instruction ->
                val result = mask.applyTo(instruction.value)
                memory.add(instruction.address, result.toDecimal())
            }
        }
        return memory.sum()
    }

    private class Memory {
        private val values = mutableListOf<Long>()

        fun add(index: Int, value: Long) {
            if (index > values.size - 1) {
                values.apply { repeat(index - values.size + 1) { add(0) } }
            }
            values[index] = value
        }

        fun sum(): Long = values.sum()
    }
}