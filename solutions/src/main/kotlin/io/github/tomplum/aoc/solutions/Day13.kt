package io.github.tomplum.aoc.solutions

import io.github.tomplum.aoc.Solution
import io.github.tomplum.aoc.bus.BusScheduler
import io.github.tomplum.aoc.bus.BusTimetable
import io.github.tomplum.libs.input.Day
import io.github.tomplum.libs.input.InputReader

class Day13 : Solution<Int, Int> {
    private val input = InputReader.read<String>(Day(13))

    override fun part1(): Int {
        val timetable = BusTimetable.fromNotes(input.value)
        return BusScheduler(timetable).getEarliestBus()
    }
}