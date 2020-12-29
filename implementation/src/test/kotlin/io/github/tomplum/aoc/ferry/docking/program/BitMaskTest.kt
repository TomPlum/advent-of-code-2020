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
        fun twoFloatingBits() {
            val mask = BitMask("000000000000000000000000000000X1001X")
            val results = mask.applyFloatingTo(42).map { it.toLong(2) }
            assertThat(results).containsOnly(26L, 27L, 58L, 59L)
        }

        @Test
        fun threeFloatingBits() {
            val mask = BitMask("00000000000000000000000000000000X0XX")
            val results = mask.applyFloatingTo(26).map { it.toLong(2) }
            assertThat(results).containsOnly(16L,17L,18L,19L,24L,25L,26L,27L)
        }
    }
}
