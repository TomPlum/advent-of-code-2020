package io.github.tomplum.aoc.solutions

import io.github.tomplum.aoc.Solution
import io.github.tomplum.aoc.airport.train.ticket.TicketReader
import io.github.tomplum.aoc.airport.train.ticket.TicketScanner
import io.github.tomplum.libs.input.Day
import io.github.tomplum.libs.input.InputReader

class Day16 : Solution<Int, Long> {
    private val input = InputReader.read<String>(Day(16)).asSingleString()
    private val document = TicketReader.read(input)

    override fun part1(): Int {
        return TicketScanner(document).getErrorRate()
    }

    override fun part2(): Long {
        return TicketScanner(document).scan("departure")
    }
}