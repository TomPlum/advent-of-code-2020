package io.github.tomplum.aoc.airport.boarding

import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class BoardingPassTest {
    @Nested
    inner class Examples {
        @Test
        fun one() {
            assertThat(BoardingPass(44, 5).getSeatID()).isEqualTo(357)
        }

        @Test
        fun two() {
            assertThat(BoardingPass(70, 7).getSeatID()).isEqualTo(567)
        }

        @Test
        fun three() {
            assertThat(BoardingPass(14, 7).getSeatID()).isEqualTo(119)
        }

        @Test
        fun four() {
            assertThat(BoardingPass(102, 4).getSeatID()).isEqualTo(820)
        }
    }
}