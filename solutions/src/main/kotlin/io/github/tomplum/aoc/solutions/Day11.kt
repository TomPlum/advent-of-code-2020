package io.github.tomplum.aoc.solutions

import io.github.tomplum.aoc.Solution
import io.github.tomplum.aoc.aircraft.seating.SeatingLayout
import io.github.tomplum.aoc.aircraft.seating.SeatingPlanSimulator
import io.github.tomplum.libs.input.Day
import io.github.tomplum.libs.input.InputReader

class Day11 : Solution<Int, Int> {
    private val input = InputReader.read<String>(Day(11))

    override fun part1(): Int {
        val initialLayout = SeatingLayout(input.value)
        val simulator = SeatingPlanSimulator(initialLayout)
        return simulator.simulateUntilConsolidated()
    }
}