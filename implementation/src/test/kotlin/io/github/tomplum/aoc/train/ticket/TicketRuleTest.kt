package io.github.tomplum.aoc.train.ticket

import assertk.assertThat
import assertk.assertions.isFalse
import assertk.assertions.isTrue
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class TicketRuleTest {
    @Nested
    inner class Apply {
        @Test
        fun isLowerBoundStart() {
            assertThat(TicketRule("test", 0..5, 8..12).apply(0)).isTrue()
        }

        @Test
        fun isLowerBoundEnd() {
            assertThat(TicketRule("test", 0..5, 8..12).apply(5)).isTrue()
        }

        @Test
        fun inLowerBound() {
            assertThat(TicketRule("test", 0..5, 8..12).apply(3)).isTrue()
        }
        @Test
        fun isUpperBoundStart() {
            assertThat(TicketRule("test", 0..5, 8..12).apply(8)).isTrue()
        }

        @Test
        fun isUpperBoundEnd() {
            assertThat(TicketRule("test", 0..5, 8..12).apply(12)).isTrue()
        }

        @Test
        fun inUpperBound() {
            assertThat(TicketRule("test", 0..5, 8..12).apply(10)).isTrue()
        }

        @Test
        fun belowLowerBound() {
            assertThat(TicketRule("test", 1..5, 8..12).apply(0)).isFalse()
        }

        @Test
        fun inBetweenBounds() {
            assertThat(TicketRule("test", 1..5, 8..12).apply(7)).isFalse()
        }

        @Test
        fun aboveUpperBound() {
            assertThat(TicketRule("test", 1..5, 8..12).apply(15)).isFalse()
        }
    }
}