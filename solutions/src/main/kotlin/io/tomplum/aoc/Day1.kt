package io.tomplum.aoc

import io.github.tomplum.aoc.Day
import io.github.tomplum.aoc.input.InputReader

class Day1 : Solution<Int> {
    override fun part1(): Int {
        val input = InputReader.read<Int>(Day(1)).value
        val report = ExpenseReport(input)
        return report.validateContainsTwoEntries()
    }
}