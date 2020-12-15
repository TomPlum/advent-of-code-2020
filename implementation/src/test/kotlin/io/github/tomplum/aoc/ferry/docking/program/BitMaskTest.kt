package io.github.tomplum.aoc.ferry.docking.program

import assertk.assertThat
import assertk.assertions.containsOnly
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class BitMaskTest {
    @Nested
    inner class ApplyMask {
        @Test
        fun exampleOne() {
            val mask = BitMask("XXXXXXXXXXXXXXXXXXXXXXXXXXXXX1XXXX0X")
            val result = mask.applyTo(11)
            assertThat(result.toLong(2)).isEqualTo(73)
        }

        @Test
        fun exampleTwo() {
            val mask = BitMask("XXXXXXXXXXXXXXXXXXXXXXXXXXXXX1XXXX0X")
            val result = mask.applyTo(101)
            assertThat(result.toLong(2)).isEqualTo(101)
        }
    }

    @Nested
    inner class ApplyFloatingMask {
        @Test
        fun example() {
            val mask = BitMask("000000000000000000000000000000X1001X")
            val results = mask.applyFloatingTo(42).map { it.toLong(2) }
            assertThat(results).containsOnly(26L, 27L, 58L, 59L)
        }
    }
}
