package io.github.tomplum.aoc.ferry.docking.program

import io.github.tomplum.aoc.ferry.docking.emulator.Memory

/**
 * The initialisation program consists of [routines] which write 36-bit unsigned integer
 * values to [Memory]. The addresses and values are driven by the [BitMask]s.
 * @param routines A map of bitmasks and their instructions.
 */
data class InitProgram(val routines: Map<BitMask, List<Instruction>>)