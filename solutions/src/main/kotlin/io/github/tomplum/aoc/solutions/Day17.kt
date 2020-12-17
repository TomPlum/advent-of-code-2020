package io.github.tomplum.aoc.solutions

import io.github.tomplum.aoc.Solution
import io.github.tomplum.aoc.aircraft.energy.DimensionalSimulator
import io.github.tomplum.aoc.aircraft.energy.PocketDimension
import io.github.tomplum.libs.input.Day
import io.github.tomplum.libs.input.InputReader

class Day17 : Solution<Int, Int> {
    private val input = InputReader.read<String>(Day(17))

    override fun part1(): Int {
        val initialState = PocketDimension(input.value)
        return DimensionalSimulator(initialState).simulate(6)
    }
}