package io.github.tomplum.aoc.solutions

import io.github.tomplum.aoc.Solution
import io.github.tomplum.aoc.airport.game.MemoryGame
import io.github.tomplum.libs.input.Day
import io.github.tomplum.libs.input.InputReader

class Day15 : Solution<Int, Int> {
    private val input = InputReader.read<String>(Day(15)).asSingleString()

    override fun part1(): Int {
        return MemoryGame(input).simulate(2020)
    }
}