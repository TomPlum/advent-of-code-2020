package io.github.tomplum.aoc.solutions

import io.github.tomplum.aoc.Solution
import io.github.tomplum.aoc.airport.boarding.EncodedBoardingPass
import io.github.tomplum.libs.input.Day
import io.github.tomplum.libs.input.InputReader

class Day5 : Solution<Int> {
    override fun part1(): Int {
        val input = InputReader.read<String>(Day(5))
        val passes = input.value.map { partitioning -> EncodedBoardingPass(partitioning).decode() }
        return passes.map { it.getSeatID() }.maxOrNull() ?: 0
    }
}