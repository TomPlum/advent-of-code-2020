package io.github.tomplum.aoc.aircraft.homework.types

import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class OperatorTest {
    @Nested
    inner class Add {
        @Test
        fun twoPositiveNumbers() {
            assertThat(Operator.ADD.apply(Number(12), Number(5))).isEqualTo(Number(17))
        }

        @Test
        fun differentSigns() {
            assertThat(Operator.ADD.apply(Number(-12), Number(5))).isEqualTo(Number(-7))
        }

        @Test
        fun twoNegativeNumbers() {
            assertThat(Operator.ADD.apply(Number(-12), Number(-6))).isEqualTo(Number(-18))
        }
    }

    @Nested
    inner class Multiply {
        @Test
        fun twoPositiveNumbers() {
            assertThat(Operator.MULTIPLY.apply(Number(12), Number(5))).isEqualTo(Number(60))
        }

        @Test
        fun differentSigns() {
            assertThat(Operator.MULTIPLY.apply(Number(-12), Number(5))).isEqualTo(Number(-60))
        }

        @Test
        fun twoNegativeNumbers() {
            assertThat(Operator.MULTIPLY.apply(Number(-12), Number(-6))).isEqualTo(Number(72))
        }
    }

    @Nested
    inner class GetLength {
        @Test
        fun add() {
            assertThat(Operator.ADD.getLength()).isEqualTo(1)
        }

        @Test
        fun multiply() {
            assertThat(Operator.MULTIPLY.getLength()).isEqualTo(1)
        }
    }

    @Nested
    inner class ToString {
        @Test
        fun add() {
            assertThat(Operator.ADD.toString()).isEqualTo("+")
        }

        @Test
        fun multiply() {
            assertThat(Operator.MULTIPLY.toString()).isEqualTo("*")
        }
    }
}