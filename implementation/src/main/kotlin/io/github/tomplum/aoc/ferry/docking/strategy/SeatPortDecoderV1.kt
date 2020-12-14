package io.github.tomplum.aoc.ferry.docking.strategy

import io.github.tomplum.aoc.extensions.toDecimal
import io.github.tomplum.aoc.ferry.docking.InitialisationProgram

class SeatPortDecoderV1: DecodingStrategy() {
    override fun decode(program: InitialisationProgram): Long = program.instructions.forEach { (mask, instructions) ->
        instructions.forEach { instruction ->
            val result = mask.applyTo(instruction.value)
            memory.add(instruction.address, result.toDecimal())
        }
    }.let { memory.sum() }
}