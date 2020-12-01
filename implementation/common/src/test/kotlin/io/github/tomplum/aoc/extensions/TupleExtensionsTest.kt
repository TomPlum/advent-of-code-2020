package io.github.tomplum.aoc.extensions

import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class TupleExtensionsTest {
    @Nested
    inner class Sum {
        @Test
        fun twoPositiveIntegers() {
            assertThat(Pair(10, 5).sum()).isEqualTo(15)
        }

        @Test
        fun twoNegativeIntegers() {
            assertThat(Pair(-4, -4).sum()).isEqualTo(-8)
        }

        @Test
        fun differentPolarity() {
            assertThat(Pair(15, -2).sum()).isEqualTo(13)
        }
    }

    @Nested
    inner class Product {
        @Test
        fun twoPositiveIntegers() {
            assertThat(Pair(10, 5).product()).isEqualTo(50)
        }

        @Test
        fun twoNegativeIntegers() {
            assertThat(Pair(-4, -4).product()).isEqualTo(16)
        }

        @Test
        fun differentPolarity() {
            assertThat(Pair(15, -2).product()).isEqualTo(-30)
        }
    }
}