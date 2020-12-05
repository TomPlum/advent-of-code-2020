package io.github.tomplum.aoc.solutions

import io.github.tomplum.aoc.Solution
import io.github.tomplum.aoc.airport.boarding.EncodedBoardingPass
import io.github.tomplum.aoc.airport.boarding.SeatFinder
import io.github.tomplum.libs.input.Day
import io.github.tomplum.libs.input.InputReader

class Day5 : Solution<Int> {
    private val input = InputReader.read<String>(Day(5))

    override fun part1(): Int = input.value.map { EncodedBoardingPass(it).decode() }.map { it.getSeatID() }.maxOrNull()!!

    override fun part2(): Int {
        val passes = input.value.map { EncodedBoardingPass(it).decode() }
        return SeatFinder().getMissingSeats(passes)
    }
}