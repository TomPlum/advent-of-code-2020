package io.github.tomplum.aoc.aircraft.seating

import assertk.assertThat
import assertk.assertions.isEqualTo
import io.github.tomplum.aoc.input.TestInputReader
import org.junit.jupiter.api.Test

class SeatingPlanSimulatorTest {
    @Test
    fun example() {
        val input = TestInputReader.read<String>("seating/example-layout.txt")
        val simulator = SeatingPlanSimulator(SeatingLayout(input.value))
        assertThat(simulator.simulateUntilConsolidated()).isEqualTo(37)
    }
}