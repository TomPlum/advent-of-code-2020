package io.github.tomplum.aoc.aircraft.seating

import assertk.assertThat
import assertk.assertions.isEqualTo
import io.github.tomplum.aoc.aircraft.seating.strategy.InitialSeatingStrategy
import io.github.tomplum.aoc.aircraft.seating.strategy.RevisedSeatingStrategy
import io.github.tomplum.aoc.input.TestInputReader
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class SeatingPlanSimulatorTest {
    @Nested
    inner class InitialStrategy {
        @Test
        fun example() {
            val input = TestInputReader.read<String>("seating/example-layout.txt")
            val simulator = SeatingPlanSimulator(SeatingLayout(input.value))
            assertThat(simulator.simulateUntilConsolidated(InitialSeatingStrategy())).isEqualTo(37)
        }
    }

    @Nested
    inner class RevisedStrategy {
        @Test
        fun example() {
            val input = TestInputReader.read<String>("seating/example-layout.txt")
            val simulator = SeatingPlanSimulator(SeatingLayout(input.value))
            assertThat(simulator.simulateUntilConsolidated(RevisedSeatingStrategy())).isEqualTo(26)
        }
    }
}