package io.github.tomplum.aoc.solutions

import io.github.tomplum.aoc.Solution
import io.github.tomplum.aoc.ferry.docking.DecoderEmulator
import io.github.tomplum.aoc.ferry.docking.ProgramParser
import io.github.tomplum.aoc.ferry.docking.strategy.SeaPortDecoderV1
import io.github.tomplum.aoc.ferry.docking.strategy.SeaPortDecoderV2
import io.github.tomplum.libs.input.Day
import io.github.tomplum.libs.input.InputReader

class Day14 : Solution <Long, Long> {
    private val input = InputReader.read<String>(Day(14))
    private val program = ProgramParser.parse(input.value)

    override fun part1(): Long {
        return DecoderEmulator(program).execute(SeaPortDecoderV1())
    }

    override fun part2(): Long {
        return DecoderEmulator(program).execute(SeaPortDecoderV2())
    }
}