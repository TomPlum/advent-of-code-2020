package io.github.tomplum.aoc.ferry.docking.strategy

import io.github.tomplum.aoc.ferry.docking.program.BitMask
import io.github.tomplum.aoc.ferry.docking.program.InitProgram

/**
 * Version 2 of the sea port decoder doesn't modify the values being written at all.
 * Instead, it acts as a memory address decoder. Immediately before a value is written to memory,
 * each bit in the bit mask modifies the corresponding bit of the destination memory address.
 * @see BitMask.applyFloatingTo
 */
class SeaPortDecoderV2: DecodingStrategy() {
    override fun decode(program: InitProgram): Long = program.routines.forEach { (mask, instructions) ->
        instructions.forEach { instruction ->
            val addresses = mask.applyFloatingTo(instruction.address).map { it.toLong(2) }
            addresses.forEach { memory.add(it, instruction.value.toLong()) }
        }
    }.let { memory.sum() }
}