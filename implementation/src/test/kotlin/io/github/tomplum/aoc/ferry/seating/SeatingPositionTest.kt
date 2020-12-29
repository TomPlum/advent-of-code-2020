package io.github.tomplum.aoc.ferry.seating

import assertk.assertThat
import assertk.assertions.isEqualTo
import assertk.assertions.isFalse
import assertk.assertions.isTrue
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class SeatingPositionTest {
    @Nested
    inner class IsEmpty {
        @Test
        fun dotCharacter() {
            assertThat(SeatingPosition('L').isEmpty()).isTrue()
        }

        @ParameterizedTest
        @ValueSource(chars = ['.', '#', '@'])
        fun wrongCharacter(value: Char) {
            assertThat(SeatingPosition(value).isEmpty()).isFalse()
        }
    }

    @Nested
    inner class IsOccupied {
        @Test
        fun dotCharacter() {
            assertThat(SeatingPosition('#').isOccupied()).isTrue()
        }

        @ParameterizedTest
        @ValueSource(chars = ['.', 'L', '@'])
        fun wrongCharacter(value: Char) {
            assertThat(SeatingPosition(value).isOccupied()).isFalse()
        }
    }

    @Nested
    inner class IsFloor {
        @Test
        fun dotCharacter() {
            assertThat(SeatingPosition('.').isFloor()).isTrue()
        }

        @ParameterizedTest
        @ValueSource(chars = ['#', 'L', '@'])
        fun wrongCharacter(value: Char) {
            assertThat(SeatingPosition(value).isFloor()).isFalse()
        }
    }

    @Nested
    inner class Companion {
        @Test
        fun floor() {
            assertThat(SeatingPosition.floor()).isEqualTo(SeatingPosition('.'))
        }

        @Test
        fun occupied() {
            assertThat(SeatingPosition.occupied()).isEqualTo(SeatingPosition('#'))
        }

        @Test
        fun empty() {
            assertThat(SeatingPosition.empty()).isEqualTo(SeatingPosition('L'))
        }
    }
}
