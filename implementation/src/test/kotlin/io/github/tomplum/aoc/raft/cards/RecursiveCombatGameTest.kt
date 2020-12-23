package io.github.tomplum.aoc.raft.cards

import assertk.assertThat
import assertk.assertions.isEqualTo
import io.github.tomplum.aoc.input.TestInputReader
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class RecursiveCombatGameTest {
    @Nested
    inner class Simulate {
        @Test
        fun examplePlayerOneWins() {
            val input = TestInputReader.read<String>("raft/game/example.txt").asSingleString()
            val decks = SpaceDeckReader.parse(input)
            assertThat(RecursiveCombatGame().simulate(decks.first, decks.second).getWinningScore()).isEqualTo(291)
        }

        @Test
        fun examplePlayerTwoWins() {
            val input = TestInputReader.read<String>("raft/game/example-2.txt").asSingleString()
            val decks = SpaceDeckReader.parse(input)
            assertThat(RecursiveCombatGame().simulate(decks.first, decks.second).getWinningScore()).isEqualTo(291)
        }

        @Test
        fun recursiveExample() {
            val input = TestInputReader.read<String>("raft/game/recursive-example.txt").asSingleString()
            val decks = SpaceDeckReader.parse(input)
            assertThat(RecursiveCombatGame().simulate(decks.first, decks.second).getWinningScore()).isEqualTo(19)
        }
    }
}