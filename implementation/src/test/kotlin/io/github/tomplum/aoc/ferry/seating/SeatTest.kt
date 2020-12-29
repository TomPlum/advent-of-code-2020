package io.github.tomplum.aoc.ferry.seating

import assertk.assertThat
import assertk.assertions.isEqualTo
import assertk.assertions.isFalse
import assertk.assertions.isTrue
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class SeatTest {
    @Nested
    inner class IsEmpty {
        @Test
        fun dotCharacter() {
            assertThat(Seat('L').isEmpty()).isTrue()
        }

        @ParameterizedTest
        @ValueSource(chars = ['.', '#', '@'])
        fun wrongCharacter(value: Char) {
            assertThat(Seat(value).isEmpty()).isFalse()
        }
    }

    @Nested
    inner class IsOccupied {
        @Test
        fun dotCharacter() {
            assertThat(Seat('#').isOccupied()).isTrue()
        }

        @ParameterizedTest
        @ValueSource(chars = ['.', 'L', '@'])
        fun wrongCharacter(value: Char) {
            assertThat(Seat(value).isOccupied()).isFalse()
        }
    }

    @Nested
    inner class IsFloor {
        @Test
        fun dotCharacter() {
            assertThat(Seat('.').isFloor()).isTrue()
        }

        @ParameterizedTest
        @ValueSource(chars = ['#', 'L', '@'])
        fun wrongCharacter(value: Char) {
            assertThat(Seat(value).isFloor()).isFalse()
        }
    }

    @Nested
    inner class Companion {
        @Test
        fun floor() {
            assertThat(Seat.floor()).isEqualTo(Seat('.'))
        }

        @Test
        fun occupied() {
            assertThat(Seat.occupied()).isEqualTo(Seat('#'))
        }

        @Test
        fun empty() {
            assertThat(Seat.empty()).isEqualTo(Seat('L'))
        }
    }
}
