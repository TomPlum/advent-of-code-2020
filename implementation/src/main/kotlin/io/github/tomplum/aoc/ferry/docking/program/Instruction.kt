package io.github.tomplum.aoc.ferry.docking.program

import io.github.tomplum.aoc.ferry.docking.emulator.Memory

/**
 * A single instruction in an [InitProgram].
 * @param address The target [Memory] address to write to.
 * @param value The value to write into the [address].
 */
data class Instruction(val address: Int, val value: Int)