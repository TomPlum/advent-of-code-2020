package io.github.tomplum.aoc.raft.cards

import assertk.assertThat
import assertk.assertions.containsExactly
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class SpaceCardDeckTest {
    @Nested
    inner class Initialise {
        @Test
        fun startingOrder() {
            assertThat(SpaceCardDeck(listOf(3,4,1,7)).cards).containsExactly(3,4,1,7)
        }
    }

    @Nested
    inner class Add {
        @Test
        fun addPairShouldGoToBottom() {
            val deck = SpaceCardDeck(listOf(1,2,3,4,5))
            deck.add(Pair(8,9))
            assertThat(deck.cards).containsExactly(1,2,3,4,5,8,9)
        }
    }
}