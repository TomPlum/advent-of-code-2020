package io.tomplum.aoc

import io.github.tomplum.aoc.Day
import io.github.tomplum.aoc.input.InputReader

class Day1 : Solution<Int> {
    private val input = InputReader.read<Int>(Day(1)).value

    override fun part1(): Int {
        return ExpenseReport(input).validateContainsTwoEntries()
    }

    override fun part2(): Int {
        return ExpenseReport(input).validateContainsThreeEntries()
    }
}