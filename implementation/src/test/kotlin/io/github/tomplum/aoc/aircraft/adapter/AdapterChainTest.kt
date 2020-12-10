package io.github.tomplum.aoc.aircraft.adapter

import assertk.assertThat
import assertk.assertions.isEqualTo
import io.github.tomplum.aoc.input.TestInputReader
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class AdapterChainTest {
    @Nested
    inner class JoltageDelta {
        @Test
        fun example() {
            val data = TestInputReader.read<Int>("adapter/example.txt").value
            val chain = AdapterChain(data)
            assertThat(chain.getJoltageDelta()).isEqualTo(35)
        }

        @Test
        fun largerExample() {
            val data = TestInputReader.read<Int>("adapter/larger-example.txt").value
            val chain = AdapterChain(data)
            assertThat(chain.getJoltageDelta()).isEqualTo(220)
        }
    }

    @Nested
    inner class AdapterArrayCombinations {
        @Test
        fun example() {
            val data = TestInputReader.read<Int>("adapter/example.txt").value
            val chain = AdapterChain(data)
            assertThat(chain.getCombinations()).isEqualTo(8)
        }

        @Test
        fun largerExample() {
            val data = TestInputReader.read<Int>("adapter/larger-example.txt").value
            val chain = AdapterChain(data)
            assertThat(chain.getCombinations()).isEqualTo(19208)
        }
    }
}