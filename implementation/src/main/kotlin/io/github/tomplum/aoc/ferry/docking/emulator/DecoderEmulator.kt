package io.github.tomplum.aoc.ferry.docking.emulator

import io.github.tomplum.aoc.ferry.docking.program.InitProgram
import io.github.tomplum.aoc.ferry.docking.strategy.DecodingStrategy

class DecoderEmulator(private val program: InitProgram) {
    fun execute(strategy: DecodingStrategy): Long = strategy.decode(program)
}