package io.github.tomplum.aoc.ferry.docking.emulator

import io.github.tomplum.aoc.ferry.docking.program.InitProgram
import io.github.tomplum.aoc.ferry.docking.strategy.DecodingStrategy

/**
 * Due to the lack of the correct decoder chip, this emulator can emulate the software of
 * an [InitProgram].
 * @param program The program to be emulated.
 */
class DecoderEmulator(private val program: InitProgram) {
    /**
     * Uses the given decoding [strategy] to emulate the [program].
     * @return The sum of the [Memory] values after emulation.
     */
    fun execute(strategy: DecodingStrategy): Long = strategy.decode(program)
}