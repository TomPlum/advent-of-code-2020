package io.github.tomplum.aoc.password.strategy

import assertk.assertThat
import assertk.assertions.isEqualTo
import assertk.assertions.isFalse
import assertk.assertions.isNotEqualTo
import assertk.assertions.isTrue
import io.github.tomplum.aoc.password.strategy.SledRentalPolicy
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class SledRentalPolicyTest {
    @Nested
    inner class Apply {
        @Test
        fun exampleOne() {
            val policy = SledRentalPolicy("1-3 a")
            val isValid = policy.apply("abcde")
            assertThat(isValid).isTrue()
        }

        @Test
        fun exampleTwo() {
            val policy = SledRentalPolicy("1-3 b")
            val isValid = policy.apply("cdefg")
            assertThat(isValid).isFalse()
        }

        @Test
        fun exampleThree() {
            val policy = SledRentalPolicy("2-9 c")
            val isValid = policy.apply("ccccccccc")
            assertThat(isValid).isTrue()
        }
    }

    @Nested
    inner class Equality {
        @Test
        fun equal() {
            val first = SledRentalPolicy("1-3 a")
            val second = SledRentalPolicy("1-3 a")
            assertThat(first).isEqualTo(second)
        }

        @Test
        fun differentMandatoryCharacter() {
            val first = SledRentalPolicy("1-3 a")
            val second = SledRentalPolicy("1-3 b")
            assertThat(first).isNotEqualTo(second)
        }

        @Test
        fun differentMinimumOccurrences() {
            val first = SledRentalPolicy("1-3 a")
            val second = SledRentalPolicy("0-3 a")
            assertThat(first).isNotEqualTo(second)
        }

        @Test
        fun differentMaximumOccurrences() {
            val first = SledRentalPolicy("1-3 a")
            val second = SledRentalPolicy("1-5 a")
            assertThat(first).isNotEqualTo(second)
        }
    }

    @Nested
    inner class ToString {
        @Test
        fun toStringTest() {
            val policy = SledRentalPolicy("1-3 b")
            val string = policy.toString()
            assertThat(string).isEqualTo("1-3 b")
        }
    }
}