package io.github.tomplum.aoc.solutions

import io.github.tomplum.libs.solutions.Solution
import io.github.tomplum.aoc.ferry.seating.SeatingLayout
import io.github.tomplum.aoc.ferry.seating.SeatingPlanSimulator
import io.github.tomplum.aoc.ferry.seating.strategy.InitialSeatingStrategy
import io.github.tomplum.aoc.ferry.seating.strategy.RevisedSeatingStrategy
import io.github.tomplum.libs.input.Day
import io.github.tomplum.libs.input.InputReader

class Day11 : Solution<Int, Int> {
    private val input = InputReader.read<String>(Day(11))

    override fun part1(): Int {
        val initialLayout = SeatingLayout(input.value)
        val simulator = SeatingPlanSimulator(initialLayout)
        return simulator.simulateUntilConsolidated(InitialSeatingStrategy())
    }

    override fun part2(): Int {
        val initialLayout = SeatingLayout(input.value)
        val simulator = SeatingPlanSimulator(initialLayout)
        return simulator.simulateUntilConsolidated(RevisedSeatingStrategy())
    }
}