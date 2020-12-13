package io.github.tomplum.aoc.solutions

import io.github.tomplum.aoc.Solution
import io.github.tomplum.aoc.bus.BusScheduler
import io.github.tomplum.aoc.bus.BusTimetable
import io.github.tomplum.libs.input.Day
import io.github.tomplum.libs.input.InputReader

class Day13 : Solution<Int, Long> {
    private val input = InputReader.read<String>(Day(13))
    private val timetable = BusTimetable.fromNotes(input.value)

    override fun part1(): Int {
        return BusScheduler(timetable).getEarliestBus()
    }

    override fun part2(): Long {
        return BusScheduler(timetable).getOffsetDepartureTime()
    }
}