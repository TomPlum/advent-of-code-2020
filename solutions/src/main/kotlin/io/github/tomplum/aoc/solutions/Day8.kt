package io.github.tomplum.aoc.solutions

import io.github.tomplum.aoc.Solution
import io.github.tomplum.aoc.bootcode.BootCodeParser
import io.github.tomplum.aoc.bootcode.BootCodeRuntime
import io.github.tomplum.libs.input.Day
import io.github.tomplum.libs.input.InputReader

class Day8 : Solution<Int> {
    override fun part1(): Int? {
        val input = InputReader.read<String>(Day(8))
        val program = BootCodeParser.parse(input.value)
        return BootCodeRuntime(program).runOnce()
    }
}