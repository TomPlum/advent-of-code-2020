package io.github.tomplum.aoc.aircraft.energy

import assertk.assertThat
import assertk.assertions.isEqualTo
import io.github.tomplum.aoc.input.TestInputReader
import org.junit.jupiter.api.Test

class DimensionalSimulatorTest {
    @Test
    fun example() {
        val input = TestInputReader.read<String>("dimension/example-state.txt")
        val initialState = PocketDimension3D(input.value)
        assertThat(DimensionalSimulator(initialState).simulate(6)).isEqualTo(112)
    }

    @Test
    fun example4D() {
        val input = TestInputReader.read<String>("dimension/example-state.txt")
        val initialState = PocketDimension4D(input.value)
        assertThat(DimensionalSimulator(PocketDimension3D(emptyList())).simulate4d(6, initialState)).isEqualTo(848)
    }
}