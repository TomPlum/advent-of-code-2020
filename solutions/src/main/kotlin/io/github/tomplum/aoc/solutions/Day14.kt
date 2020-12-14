package io.github.tomplum.aoc.solutions

import io.github.tomplum.aoc.Solution
import io.github.tomplum.aoc.ferry.docking.DecoderEmulator
import io.github.tomplum.aoc.ferry.docking.ProgramParser
import io.github.tomplum.aoc.ferry.docking.strategy.SeaPortDecoderV1
import io.github.tomplum.libs.input.Day
import io.github.tomplum.libs.input.InputReader

class Day14 : Solution <Long, Int> {
    private val input = InputReader.read<String>(Day(14))

    override fun part1(): Long {
        val program = ProgramParser.parse(input.value)
        val emulator = DecoderEmulator(program)
        return emulator.execute(SeaPortDecoderV1())
    }
}