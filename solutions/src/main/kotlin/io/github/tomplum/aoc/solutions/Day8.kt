package io.github.tomplum.aoc.solutions

import io.github.tomplum.aoc.Solution
import io.github.tomplum.aoc.bootcode.BootCodeParser
import io.github.tomplum.aoc.bootcode.ProgramRepairAgent
import io.github.tomplum.aoc.bootcode.Runtime
import io.github.tomplum.libs.input.Day
import io.github.tomplum.libs.input.InputReader

class Day8 : Solution<Int> {
    private val input = InputReader.read<String>(Day(8))

    override fun part1(): Int? {
        val program = BootCodeParser.parse(input.value)
        return Runtime(program).runOnce()
    }

    override fun part2(): Int? {
        val corruptProgram = BootCodeParser.parse(input.value)
        val repairedProgram = ProgramRepairAgent().fix(corruptProgram)
        return Runtime(repairedProgram).run()
    }
}