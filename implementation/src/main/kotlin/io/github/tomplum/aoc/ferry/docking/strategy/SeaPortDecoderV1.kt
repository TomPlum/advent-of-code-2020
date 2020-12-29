package io.github.tomplum.aoc.ferry.docking.strategy

import io.github.tomplum.aoc.ferry.docking.program.InitProgram
import io.github.tomplum.aoc.ferry.docking.program.Instruction
import io.github.tomplum.aoc.ferry.docking.emulator.Memory

/**
 * Version 1 of the sea port decoder directly stores the masked value of each [Instruction] at
 * the specified address in [Memory].
 */
class SeaPortDecoderV1: DecodingStrategy() {
    override fun decode(program: InitProgram): Long = program.routines.forEach { (mask, instructions) ->
        instructions.forEach { instruction ->
            val result = mask.applyTo(instruction.value)
            memory.add(instruction.address.toLong(), result.toLong(2))
        }
    }.let { memory.sum() }
}