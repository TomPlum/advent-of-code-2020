package io.github.tomplum.aoc.solutions

import io.github.tomplum.libs.solutions.Solution
import io.github.tomplum.aoc.airport.game.MemoryGame
import io.github.tomplum.libs.input.Day
import io.github.tomplum.libs.input.InputReader

class Day15 : Solution<Int, Int> {
    private val input = InputReader.read<String>(Day(15)).asSingleString()

    override fun part1(): Int {
        return MemoryGame(input, 2020).simulate()
    }

    override fun part2(): Int {
        return MemoryGame(input, 30_000_000).simulate()
    }
}