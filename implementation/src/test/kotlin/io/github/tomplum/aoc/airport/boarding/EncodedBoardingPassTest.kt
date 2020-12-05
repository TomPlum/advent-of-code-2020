package io.github.tomplum.aoc.airport.boarding

import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class EncodedBoardingPassTest {
    @Nested
    inner class Examples {
        @Test
        fun one() {
            assertThat(EncodedBoardingPass("FBFBBFFRLR").decode()).isEqualTo(BoardingPass(44, 5))
        }

        @Test
        fun two() {
            assertThat(EncodedBoardingPass("BFFFBBFRRR").decode()).isEqualTo(BoardingPass(70, 7))
        }

        @Test
        fun three() {
            assertThat(EncodedBoardingPass("FFFBBBFRRR").decode()).isEqualTo(BoardingPass(14, 7))
        }

        @Test
        fun four() {
            assertThat(EncodedBoardingPass("BBFFBBFRLL").decode()).isEqualTo(BoardingPass(102, 4))
        }
    }
}