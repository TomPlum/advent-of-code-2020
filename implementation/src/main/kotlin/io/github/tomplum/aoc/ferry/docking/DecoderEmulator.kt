package io.github.tomplum.aoc.ferry.docking

import io.github.tomplum.aoc.extensions.toBinary
import io.github.tomplum.aoc.extensions.toDecimal

class DecoderEmulator(private val program: InitialisationProgram) {
    private val memory = IntArray(36)

    fun execute(): Int {
        program.instructions.forEach { (mask, instructions) ->
            instructions.forEach { instruction ->
                val result = mask.applyTo(instruction.value.toBinary(36))
                memory[instruction.address] = result.toDecimal()
            }
        }
        return memory.sum()
    }
}