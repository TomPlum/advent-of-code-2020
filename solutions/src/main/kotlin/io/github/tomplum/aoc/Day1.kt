package io.github.tomplum.aoc

import io.github.tomplum.aoc.input.InputReader
import io.github.tomplum.aoc.password.report.ExpenseReport

class Day1 : Solution<Int> {
    private val input = InputReader.read<Int>(Day(1)).value

    override fun part1(): Int {
        return ExpenseReport(input).validateContainsTwoEntries()
    }

    override fun part2(): Int {
        return ExpenseReport(input).validateContainsThreeEntries()
    }
}