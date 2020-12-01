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
            assertThat(listOf(0, 1).cartesianProduct()).containsAll(Pair(0,0), Pair(1,1),Pair(0,1), Pair(1,0))
        }

        @Test
        fun emptySet() {
            assertThat(emptyList<Int>().cartesianProduct()).isEmpty()
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
    inner class CartesianProductTripleSelf {
        @Test
        fun example() {
            val cartesianProduct = listOf(-1, -2).cartesianProduct(listOf(1, 2), listOf(0))
            assertThat(cartesianProduct).containsAll(Triple(-1,1,0), Triple(-1,2,0), Triple(-2,1,0), Triple(-2,2,0))
        }
    }
}