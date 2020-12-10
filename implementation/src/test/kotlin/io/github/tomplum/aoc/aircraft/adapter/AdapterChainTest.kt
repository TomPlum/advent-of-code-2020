package io.github.tomplum.aoc.aircraft.adapter

import assertk.assertThat
import assertk.assertions.isEqualTo
import io.github.tomplum.aoc.input.TestInputReader
import org.junit.jupiter.api.Test

class AdapterChainTest {
    @Test
    fun example() {
        val data = TestInputReader.read<Int>("adapter/example.txt").value
        val chain = AdapterChain(data)
        assertThat(chain.getJoltageDelta()).isEqualTo(35)
    }
}