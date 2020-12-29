package io.github.tomplum.aoc.aircraft.homework.types

import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class NumberTest {
    @Nested
    inner class ToString {
        @Test
        fun example() {
            assertThat(Number(10).toString()).isEqualTo("10")
        }
    }

    @Nested
    inner class GetLength {
        @ParameterizedTest
        @ValueSource(longs = [0, 12, 425, 1289, 56912])
        fun oneDigit() {
            assertThat(Number(1).getLength()).isEqualTo(1)
        }
    }
}