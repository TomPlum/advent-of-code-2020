package io.github.tomplum.aoc.raft.cups

import assertk.assertThat
import assertk.assertions.containsExactly
import assertk.assertions.isEqualTo
import assertk.assertions.isNotEqualTo
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class CupCircleTest {
    @Nested
    inner class GetClockwiseCups {
        @Test
        fun firstCupIsCurrent() {
            assertThat(CupCircle(listOf(3,8,9,1,2,5,4,6,7)).pickClockwiseCups(3)).containsExactly(8,9,1)
        }

        @Test
        fun currentCupFourthFromEnd() {
            assertThat(CupCircle(listOf(3,8,9,1,2,5,4,6,7)).pickClockwiseCups(5)).containsExactly(4,6,7)
        }

        @Test
        fun currentCupThirdFromEnd() {
            assertThat(CupCircle(listOf(3,8,9,1,2,5,4,6,7)).pickClockwiseCups(4)).containsExactly(6,7,3)
        }

        @Test
        fun currentCupSecondFromEnd() {
            assertThat(CupCircle(listOf(3,8,9,1,2,5,4,6,7)).pickClockwiseCups(6)).containsExactly(7,3,8)
        }

        @Test
        fun endCupIsCurrent() {
            assertThat(CupCircle(listOf(3,8,9,1,2,5,4,6,7)).pickClockwiseCups(7)).containsExactly(3,8,9)
        }
    }

    @Nested
    inner class GetDestinationCup {
        @Test
        fun destinationInPickedAndGoesBelowLowestCupValue() {
            val cupCircle = CupCircle(listOf(3, 2, 8, 9, 1, 5, 4, 6, 7))
            val destinationCup = cupCircle.getDestinationCup(listOf(8, 9, 1), 2)
            assertThat(destinationCup).isEqualTo(7)
        }
    }

    @Nested
    inner class Equality {
        @Test
        fun sameCupsSameOrder() {
            assertThat(CupCircle(listOf(1,2,3))).isEqualTo(CupCircle(listOf(1,2,3)))
        }

        @Test
        fun differentCups() {
            assertThat(CupCircle(listOf(1,2,3))).isNotEqualTo(CupCircle(listOf(3,4,5)))
        }

        @Test
        fun sameCupsDifferentOrder() {
            assertThat(CupCircle(listOf(1,2,3))).isNotEqualTo(listOf(2,1,3))
        }

        @Test
        fun differentType() {
            assertThat(CupCircle(listOf(1,2,3))).isNotEqualTo(listOf(1,2,3))
        }
    }

    @Nested
    inner class ToStringCurrent {
        @Test
        fun example() {
            assertThat(CupCircle(listOf(5,1,3,2,4)).toString(3)).isEqualTo("5  1  (3)  2  4")
        }
    }
}