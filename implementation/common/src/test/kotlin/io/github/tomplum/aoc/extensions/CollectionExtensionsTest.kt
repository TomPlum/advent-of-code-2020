package io.github.tomplum.aoc.extensions

import assertk.assertThat
import assertk.assertions.containsAll
import assertk.assertions.isEmpty
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class CollectionExtensionsTest {
    @Nested
    inner class CartesianProductSelf {
        @Test
        fun twoElements() {
            assertThat(listOf(0, 1).cartesianProductQuadratic()).containsAll(Pair(0,0), Pair(1,1),Pair(0,1), Pair(1,0))
        }

        @Test
        fun emptySet() {
            assertThat(emptyList<Int>().cartesianProductQuadratic()).isEmpty()
        }
    }

    @Nested
    inner class CartesianProductOther {
        @Test
        fun twoElementsInBoth() {
            val cartesianProduct = listOf(0, 1).cartesianProduct(listOf(2, 3))
            assertThat(cartesianProduct).containsAll(Pair(0,2), Pair(0,3),Pair(1,2), Pair(1,3))
        }

        @Test
        fun emptySets() {
            assertThat(emptyList<Int>().cartesianProduct(emptyList<Int>())).isEmpty()
        }
    }

    @Nested
    inner class CartesianProductTripleOther {
        @Test
        fun example() {
            val cartesianProduct = listOf(-1, -2).cartesianProductCubic(listOf(1, 2), listOf(0))
            assertThat(cartesianProduct).containsAll(Triple(-1,1,0), Triple(-1,2,0), Triple(-2,1,0), Triple(-2,2,0))
        }

        @Test
        fun emptySets() {
            assertThat(emptyList<Int>().cartesianProductCubic(emptyList(), emptyList())).isEmpty()
        }
    }

    @Nested
    inner class ListProduct {
        @Test
        fun empty() {
            assertThat(emptyList<Int>().product()).isEqualTo(0)
        }

        @Test
        fun severalElements() {
            assertThat(listOf(2, 5, 10).product()).isEqualTo(100)
        }

        @Test
        fun negativeIntegers() {
            assertThat(listOf(3, 10, 2, -1).product()).isEqualTo(-60)
        }
    }

    @Nested
    inner class BinaryIntArrayToDecimal {
        @Test
        fun example() {
            val binary = IntArray(36)
            binary[35] = 1
            binary[34] = 1
            binary[32] = 1
            assertThat(binary.toDecimal()).isEqualTo(11)
        }
    }
}