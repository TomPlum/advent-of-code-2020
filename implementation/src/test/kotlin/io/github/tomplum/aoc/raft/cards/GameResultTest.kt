package io.github.tomplum.aoc.raft.cards

import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class GameResultTest {
    @Nested
    inner class GetWinningScore() {
        @Test
        fun playerOneWins() {
            val result = GameResult(Player.PLAYER_1, SpaceCardDeck(listOf(1,2,3)), SpaceCardDeck(listOf(3,4,5)))
            val winningScore = result.getWinningScore()
            assertThat(winningScore).isEqualTo(10)
        }

        @Test
        fun playerTwoWins() {
            val result = GameResult(Player.PLAYER_2, SpaceCardDeck(listOf(1,2,3)), SpaceCardDeck(listOf(3,4,5)))
            val winningScore = result.getWinningScore()
            assertThat(winningScore).isEqualTo(22)
        }
    }
}