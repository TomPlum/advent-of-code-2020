package io.github.tomplum.aoc.raft.cards

import assertk.assertThat
import assertk.assertions.isEqualTo
import io.github.tomplum.aoc.input.TestInputReader
import org.junit.jupiter.api.Test

class CombatGameTest {
    @Test
    fun example() {
        val input = TestInputReader.read<String>("raft/cards/example.txt").asSingleString()
        val decks = SpaceDeckReader.parse(input)
        assertThat(CombatGame(decks.first, decks.second).simulate()).isEqualTo(306)
    }

    @Test
    fun exampleTwo() {
        val input = TestInputReader.read<String>("raft/cards/example-2.txt").asSingleString()
        val decks = SpaceDeckReader.parse(input)
        assertThat(CombatGame(decks.first, decks.second).simulate()).isEqualTo(306)
    }
}