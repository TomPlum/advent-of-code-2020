package io.github.tomplum.aoc.solutions

import io.github.tomplum.aoc.Solution
import io.github.tomplum.aoc.raft.cards.CombatGameReader
import io.github.tomplum.libs.input.Day
import io.github.tomplum.libs.input.InputReader

class Day22 : Solution<Int, Int> {
    private val input = InputReader.read<String>(Day(22)).asSingleString()

    override fun part1(): Int {
        val game = CombatGameReader.parse(input)
        return game.simulate()
    }
}