package io.github.tomplum.aoc.solutions

import io.github.tomplum.libs.solutions.Solution
import io.github.tomplum.aoc.raft.cups.CupGame
import io.github.tomplum.aoc.raft.cups.TranslatedCupGame
import io.github.tomplum.libs.input.Day
import io.github.tomplum.libs.input.InputReader

class Day23 : Solution<String, Long> {
    private val input = InputReader.read<String>(Day(23)).asSingleString()

    override fun part1(): String {
        return CupGame(input).simulate(100)
    }

    override fun part2(): Long {
        return TranslatedCupGame(input).simulate(10_000_000)
    }
}