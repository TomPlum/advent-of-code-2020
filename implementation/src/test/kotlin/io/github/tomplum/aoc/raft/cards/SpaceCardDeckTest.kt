package io.github.tomplum.aoc.raft.cards

import assertk.assertThat
import assertk.assertions.*
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class SpaceCardDeckTest {
    @Nested
    inner class Initialise {
        @Test
        fun startingOrder() {
            assertThat(SpaceCardDeck(listOf(3,4,1,7)).getCards()).containsExactly(3,4,1,7)
        }

        @Test
        fun emptyStartingCards() {
            assertThat(SpaceCardDeck(emptyList()).getCards()).isEmpty()
        }
    }

    @Nested
    inner class Add {
        @Test
        fun addPairShouldGoToBottom() {
            val deck = SpaceCardDeck(listOf(1,2,3,4,5))
            deck.add(Pair(8,9))
            assertThat(deck.getCards()).containsExactly(1,2,3,4,5,8,9)
        }
    }

    @Nested
    inner class Draw {
        @Test
        fun singleCardDeckShouldTakeOnlyCard() {
            assertThat(SpaceCardDeck(listOf(2)).draw()).isEqualTo(2)
        }

        @Test
        fun multipleCardsShouldTakeTop() {
            assertThat(SpaceCardDeck(listOf(4,5,1,2)).draw()).isEqualTo(4)
        }

        @Test
        fun emptyDeck() {
            assertThrows<NullPointerException> { SpaceCardDeck(emptyList()).draw() }
        }

        @Test
        fun shouldRemoveTopCard() {
            val deck = SpaceCardDeck(listOf(3,4,6))
            deck.draw()
            assertThat(deck.getCards()).containsExactly(4,6)
        }
    }

    @Nested
    inner class IsEmpty {
        @Test
        fun isEmpty() {
            assertThat(SpaceCardDeck(emptyList()).isEmpty()).isTrue()
        }

        @Test
        fun isPopulated() {
            assertThat(SpaceCardDeck(listOf(1,2,3)).isEmpty()).isFalse()
        }
    }

    @Nested
    inner class GetDeckScore {
        @Test
        fun example() {
            assertThat(SpaceCardDeck(listOf(3, 2, 10, 6, 8, 5, 9, 4, 7, 1)).getDeckScore()).isEqualTo(306)
        }
    }
}