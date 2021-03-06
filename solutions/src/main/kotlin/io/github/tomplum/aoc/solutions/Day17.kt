package io.github.tomplum.aoc.solutions

import io.github.tomplum.libs.solutions.Solution
import io.github.tomplum.aoc.aircraft.energy.DimensionalSimulator
import io.github.tomplum.aoc.aircraft.energy.PocketDimension3D
import io.github.tomplum.aoc.aircraft.energy.PocketDimension4D
import io.github.tomplum.libs.input.Day
import io.github.tomplum.libs.input.InputReader

class Day17 : Solution<Int, Int> {
    private val input = InputReader.read<String>(Day(17))

    override fun part1(): Int {
        val initialState = PocketDimension3D(input.value)
        return DimensionalSimulator(initialState).simulate(6)
    }

    override fun part2(): Int {
        val initialState = PocketDimension4D(input.value)
        return DimensionalSimulator(initialState).simulate(6)
    }
}