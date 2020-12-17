package io.github.tomplum.aoc.aircraft.energy

import assertk.assertThat
import assertk.assertions.isEqualTo
import io.github.tomplum.aoc.input.TestInputReader
import org.junit.jupiter.api.Test

class DimensionalSimulatorTest {
    @Test
    fun example() {
        val input = TestInputReader.read<String>("dimension/example-state.txt")
        val initialState = PocketDimension(input.value)
        assertThat(DimensionalSimulator(initialState).simulate(6)).isEqualTo(112)
    }
}