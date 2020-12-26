package io.github.tomplum.aoc.solutions

import io.github.tomplum.aoc.Solution
import io.github.tomplum.aoc.raft.cards.CombatGame
import io.github.tomplum.aoc.raft.cards.RecursiveCombatGame
import io.github.tomplum.aoc.raft.cards.SpaceDeckReader
import io.github.tomplum.libs.input.Day
import io.github.tomplum.libs.input.InputReader

class Day22 : Solution<Int, Int> {
    private val input = InputReader.read<String>(Day(22)).asSingleString()

    override fun part1(): Int {
        val decks = SpaceDeckReader.parse(input)
        return CombatGame(decks.first, decks.second).simulate()
    }

    override fun part2(): Int {
        val decks = SpaceDeckReader.parse(input)
        val result = RecursiveCombatGame().simulate(decks.first, decks.second)
        return result.getWinningScore()
    }
}