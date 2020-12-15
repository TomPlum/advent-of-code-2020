package io.github.tomplum.aoc.ferry.docking.strategy

import io.github.tomplum.aoc.ferry.docking.program.InitProgram
import io.github.tomplum.aoc.ferry.docking.emulator.Memory

/**
 * A strategy for emulating a decoder-chip.
 * @property memory The segment of memory used by the emulator.
 */
abstract class DecodingStrategy {
    protected val memory = Memory()

    /**
     * Runs the given [program] routines and decodes the output.
     * @return The sum of all values in [memory] after executing.
     */
    abstract fun decode(program: InitProgram): Long
}