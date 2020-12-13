package io.github.tomplum.aoc.solutions

import io.github.tomplum.aoc.Solution
import io.github.tomplum.aoc.bus.BusScheduler
import io.github.tomplum.aoc.bus.ScheduleNotes
import io.github.tomplum.libs.input.Day
import io.github.tomplum.libs.input.InputReader

class Day13 : Solution<Int, Int> {
    private val input = InputReader.read<String>(Day(13))

    override fun part1(): Int {
        val notes = ScheduleNotes.fromString(input.value)
        return BusScheduler(notes).getEarliestBus()
    }
}