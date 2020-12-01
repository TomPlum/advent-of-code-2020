package io.tomplum.aoc

import java.io.File

class Day1 : Solution<Int> {
    override fun part1(): Int {
        val lines = File(Day1::class.java.getResource("/day1/input.txt").path).readLines().map { it.toInt() }
        val report = ExpenseReport(lines)
        return report.repair()
    }
}