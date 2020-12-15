package io.github.tomplum.aoc.ferry.docking.strategy

import io.github.tomplum.aoc.ferry.docking.InitProgram

class SeaPortDecoderV2: DecodingStrategy() {
    override fun decode(program: InitProgram): Long = program.routines.forEach { (mask, instructions) ->
        instructions.forEach { instruction ->
            val addresses = mask.applyFloatingTo(instruction.address).map { it.toLong(2) }
            addresses.forEach { memory.add(it, instruction.value.toLong()) }
        }
    }.let { memory.sum() }
}