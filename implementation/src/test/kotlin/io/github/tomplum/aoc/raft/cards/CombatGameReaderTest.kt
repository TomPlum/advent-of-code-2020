package io.github.tomplum.aoc.raft.cards

import assertk.assertThat
import assertk.assertions.isEqualTo
import io.github.tomplum.aoc.input.TestInputReader
import org.junit.jupiter.api.Test

class CombatGameReaderTest {
    @Test
    fun example() {
        val input = TestInputReader.read<String>("raft/game/example.txt").asSingleString()
        val game = CombatGameReader.parse(input)
        assertThat(game).isEqualTo(getExpectedCombatGame())
    }

    private fun getExpectedCombatGame(): CombatGame {
        val p1 = SpaceCardDeck(listOf(9,2,6,3,1))
        val p2 = SpaceCardDeck(listOf(5,8,4,7,10))
        return CombatGame(p1, p2)
    }
}