package io.github.tomplum.aoc.ferry.docking.strategy

import io.github.tomplum.aoc.extensions.toDecimal
import io.github.tomplum.aoc.ferry.docking.InitialisationProgram

class SeaPortDecoderV2: DecodingStrategy() {
    override fun decode(program: InitialisationProgram): Long = program.instructions.forEach { (mask, instructions) ->
        instructions.forEach { instruction ->
            val addresses = mask.applyFloatingTo(instruction.address)
            addresses.map { it.toDecimal() }.forEach { memory.add(it.toInt(), instruction.value.toLong()) }
        }
    }.let { memory.sum() }
}