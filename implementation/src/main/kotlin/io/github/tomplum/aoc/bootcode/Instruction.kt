package io.github.tomplum.aoc.bootcode

/**
 * A single instruction in a [Program].
 * @param code The operation code.
 * @param argument The numerical value of the instruction.
 */
data class Instruction(val code: Operation, val argument: Int)