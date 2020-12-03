package io.github.tomplum.aoc.solutions

import io.github.tomplum.aoc.Solution
import io.github.tomplum.aoc.report.ExpenseReport
import io.github.tomplum.libs.input.Day
import io.github.tomplum.libs.input.InputReader

class Day1 : Solution<Int> {
    private val input = InputReader.read<Int>(Day(1)).value

    override fun part1(): Int {
        return ExpenseReport(input).validateContainsTwoEntries()
    }

    override fun part2(): Int {
        return ExpenseReport(input).validateContainsThreeEntries()
    }
}