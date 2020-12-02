package io.github.tomplum.aoc.password

import assertk.assertThat
import assertk.assertions.isEqualTo
import assertk.assertions.isNotEqualTo
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

    @Nested
    inner class Equality {
        @Test
        fun equal() {
            val first = CorporatePolicy("1-3 a")
            val second = CorporatePolicy("1-3 a")
            assertThat(first).isEqualTo(second)
        }

        @Test
        fun differentMandatoryCharacter() {
            val first = CorporatePolicy("1-3 a")
            val second = CorporatePolicy("1-3 b")
            assertThat(first).isNotEqualTo(second)
        }

        @Test
        fun differentMinimumOccurrences() {
            val first = CorporatePolicy("1-3 a")
            val second = CorporatePolicy("0-3 a")
            assertThat(first).isNotEqualTo(second)
        }

        @Test
        fun differentMaximumOccurrences() {
            val first = CorporatePolicy("1-3 a")
            val second = CorporatePolicy("1-5 a")
            assertThat(first).isNotEqualTo(second)
        }
    }
}