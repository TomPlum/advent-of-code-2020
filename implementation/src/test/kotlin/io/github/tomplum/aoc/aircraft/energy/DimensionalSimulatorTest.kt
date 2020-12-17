package io.github.tomplum.aoc.aircraft.energy

import assertk.assertThat
import assertk.assertions.isEqualTo
import io.github.tomplum.aoc.input.TestInputReader
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class DimensionalSimulatorTest {
    @Nested
    inner class ThreeDimensional {
        @Test
        fun example() {
            val input = TestInputReader.read<String>("dimension/example-state.txt")
            val initialState = PocketDimension3D(input.value)
            assertThat(DimensionalSimulator(initialState).simulate(6)).isEqualTo(112)
        }

    }

    @Nested
    inner class FourDimensional {
        @Test
        fun example4D() {
            val input = TestInputReader.read<String>("dimension/example-state.txt")
            val initialState = PocketDimension4D(input.value)
            assertThat(DimensionalSimulator(initialState).simulate(6)).isEqualTo(848)
        }
    }
}