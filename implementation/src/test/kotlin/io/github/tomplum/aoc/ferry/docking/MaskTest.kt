package io.github.tomplum.aoc.ferry.docking

import assertk.assertThat
import assertk.assertions.isEqualTo
import io.github.tomplum.aoc.extensions.toDecimal
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class MaskTest {
    @Nested
    inner class ApplyMask {
        @Test
        fun exampleOne() {
            val mask = "XXXXXXXXXXXXXXXXXXXXXXXXXXXXX1XXXX0X".toMask()
            val result = mask.applyTo(11)
            assertThat(result.toDecimal()).isEqualTo(73)
        }

        @Test
        fun exampleTwo() {
            val mask = "XXXXXXXXXXXXXXXXXXXXXXXXXXXXX1XXXX0X".toMask()
            val result = mask.applyTo(101)
            assertThat(result.toDecimal()).isEqualTo(101)
        }
    }

    private fun String.toMask(): Mask {
        val mask = Mask(36)
        this.forEachIndexed { index, bit ->
            if (bit.isDigit()) {
                mask.put(index, bit.toString().toInt())
            } else {
                mask.put(index, 2)
            }
        }
        return mask
    }
}