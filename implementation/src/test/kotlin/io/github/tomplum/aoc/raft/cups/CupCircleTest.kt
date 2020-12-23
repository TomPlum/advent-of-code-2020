package io.github.tomplum.aoc.raft.cups

import assertk.assertThat
import assertk.assertions.containsExactly
import assertk.assertions.isEqualTo
import assertk.assertions.isNotEqualTo
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class CupCircleTest {
    @Nested
    inner class GetFirst {
        @Test
        fun circleContainsOneCup() {
            assertThat(CupCircle(listOf(2)).first()).isEqualTo(2)
        }

        @Test
        fun circleContainsMultipleCups() {
            assertThat(CupCircle(listOf(1,2,4)).first()).isEqualTo(1)
        }

        @Test
        fun empty() {
            assertThrows<NullPointerException> { CupCircle(emptyList()).first() }
        }

        @Test
        fun shouldReturnNotRemove() {
            val circle = CupCircle(listOf(3,5,8))
            circle.first()
            assertThat(circle.first()).isEqualTo(3)
        }
    }

    @Nested
    inner class GetClockwiseCups {
        @Test
        fun firstCupIsCurrent() {
            assertThat(CupCircle(listOf(3,8,9,1,2,5,4,6,7)).clockwiseCups(3)).containsExactly(8,9,1)
        }

        @Test
        fun currentCupFourthFromEnd() {
            assertThat(CupCircle(listOf(3,8,9,1,2,5,4,6,7)).clockwiseCups(5)).containsExactly(4,6,7)
        }

        @Test
        fun currentCupThirdFromEnd() {
            assertThat(CupCircle(listOf(3,8,9,1,2,5,4,6,7)).clockwiseCups(4)).containsExactly(6,7,3)
        }

        @Test
        fun currentCupSecondFromEnd() {
            assertThat(CupCircle(listOf(3,8,9,1,2,5,4,6,7)).clockwiseCups(6)).containsExactly(7,3,8)
        }

        @Test
        fun endCupIsCurrent() {
            assertThat(CupCircle(listOf(3,8,9,1,2,5,4,6,7)).clockwiseCups(7)).containsExactly(3,8,9)
        }

        @Test
        fun cupsShouldBeRemoved() {
            val circle = CupCircle(listOf(3,8,9,1,2,5,4,6,7))
            circle.clockwiseCups(3)
            assertThat(circle.clockwiseCups(3)).containsExactly(2,5,4)
        }
    }

    @Nested
    inner class Equality {
        @Test
        fun sameCups() {
            assertThat(CupCircle(listOf(1,2,3))).isEqualTo(CupCircle(listOf(1,2,3)))
        }

        @Test
        fun differentCups() {
            assertThat(CupCircle(listOf(1,2,3))).isNotEqualTo(CupCircle(listOf(2,3,4)))
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
            assertThat(CupCircle(listOf(6,7,1,4,9)).toString(7)).isEqualTo("6  (7)  1  4  9")
        }
    }
}