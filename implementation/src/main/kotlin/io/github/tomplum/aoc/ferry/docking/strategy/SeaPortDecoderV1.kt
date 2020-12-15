package io.github.tomplum.aoc.ferry.docking.strategy

import io.github.tomplum.aoc.ferry.docking.program.InitProgram

class SeaPortDecoderV1: DecodingStrategy() {
    override fun decode(program: InitProgram): Long = program.routines.forEach { (mask, instructions) ->
        instructions.forEach { instruction ->
            val result = mask.applyTo(instruction.value)
            memory.add(instruction.address.toLong(), result.toLong(2))
        }
    }.let { memory.sum() }
}