package io.github.tomplum.aoc.solutions

import io.github.tomplum.aoc.Solution
import io.github.tomplum.aoc.bootcode.BootCodeParser
import io.github.tomplum.aoc.bootcode.BootCodeRepairAgent
import io.github.tomplum.aoc.bootcode.BootCodeRuntime
import io.github.tomplum.libs.input.Day
import io.github.tomplum.libs.input.InputReader

class Day8 : Solution<Int> {
    private val input = InputReader.read<String>(Day(8))

    override fun part1(): Int? {
        val program = BootCodeParser.parse(input.value)
        return BootCodeRuntime(program).runOnce()
    }

    override fun part2(): Int? {
        val corruptProgram = BootCodeParser.parse(input.value)
        val repairedProgram = BootCodeRepairAgent().fix(corruptProgram)
        return BootCodeRuntime(repairedProgram).run()
    }
}