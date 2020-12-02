package io.github.tomplum.aoc.password

import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class CorporatePolicyTest {
    @Nested
    inner class MandatoryCharacter {
        @Test
        fun validCharacter() {
            val policy = CorporatePolicy("1-3 a: abcde")
            val character = policy.mandatoryCharacter
            assertThat(character).isEqualTo('a')
        }
    }

    @Nested
    inner class MinimumOccurrences {
        @Test
        fun singleDigit() {
            val policy = CorporatePolicy("0-1 b: oijasda")
            val occurrences = policy.minimumOccurrences
            assertThat(occurrences).isEqualTo(0)
        }

        @Test
        fun twoDigit() {
            val policy = CorporatePolicy("12-15 b: oijasda")
            val occurrences = policy.minimumOccurrences
            assertThat(occurrences).isEqualTo(12)
        }
    }

    @Nested
    inner class MaximumOccurrences {
        @Test
        fun singleDigit() {
            val policy = CorporatePolicy("0-1 b: oijasda")
            val occurrences = policy.maximumOccurrences
            assertThat(occurrences).isEqualTo(1)
        }

        @Test
        fun twoDigit() {
            val policy = CorporatePolicy("12-15 b: oijasda")
            val occurrences = policy.maximumOccurrences
            assertThat(occurrences).isEqualTo(15)
        }
    }
}