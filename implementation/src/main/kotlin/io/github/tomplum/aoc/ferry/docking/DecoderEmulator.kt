package io.github.tomplum.aoc.ferry.docking

import io.github.tomplum.aoc.ferry.docking.strategy.DecodingStrategy

class DecoderEmulator(private val program: InitProgram) {
    fun execute(strategy: DecodingStrategy): Long = strategy.decode(program)
}