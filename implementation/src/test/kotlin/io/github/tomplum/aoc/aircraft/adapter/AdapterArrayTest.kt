package io.github.tomplum.aoc.aircraft.adapter

import assertk.assertThat
import assertk.assertions.isEqualTo
import io.github.tomplum.aoc.input.TestInputReader
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class AdapterArrayTest {
    @Nested
    inner class JoltageDelta {
        @Test
        fun example() {
            val data = TestInputReader.read<Long>("adapter/example.txt").value
            val chain = AdapterArray(data)
            assertThat(chain.getJoltageDelta()).isEqualTo(35)
        }

        @Test
        fun largerExample() {
            val data = TestInputReader.read<Long>("adapter/larger-example.txt").value
            val chain = AdapterArray(data)
            assertThat(chain.getJoltageDelta()).isEqualTo(220)
        }
    }

    @Nested
    inner class AdapterArrayCombinations {
        @Test
        fun example() {
            val data = TestInputReader.read<Long>("adapter/example.txt").value
            val chain = AdapterArray(data)
            assertThat(chain.getCombinations()).isEqualTo(8)
        }

        @Test
        fun largerExample() {
            val data = TestInputReader.read<Long>("adapter/larger-example.txt").value
            val chain = AdapterArray(data)
            assertThat(chain.getCombinations()).isEqualTo(19208)
        }
    }
}