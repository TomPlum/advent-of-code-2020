package io.github.tomplum.aoc.solutions

import io.github.tomplum.aoc.Solution
import io.github.tomplum.aoc.forest.satellite.MessageReader
import io.github.tomplum.aoc.forest.satellite.OrRule
import io.github.tomplum.libs.input.Day
import io.github.tomplum.libs.input.InputReader

class Day19 : Solution<Int, Int> {
    private val input = InputReader.read<String>(Day(19)).asSingleString()

    override fun part1(): Int {
        val report = MessageReader.parse(input)
        return report.getMessagesMatchingRule(0)
    }

    override fun part2(): Int {
        val report = MessageReader.parse(input)
        report.replaceRules(listOf(8 to OrRule(listOf(listOf(42), listOf(42, 8))), 11 to OrRule(listOf(listOf(42, 31), listOf(42, 11, 31)))).toMap())
        return report.getMessagesMatchingRule(0)
    }
}