package io.github.tomplum.aoc.solutions

import io.github.tomplum.aoc.Solution
import io.github.tomplum.aoc.aircraft.adapter.AdapterArray
import io.github.tomplum.libs.input.Day
import io.github.tomplum.libs.input.InputReader

class Day10 : Solution<Int, Long> {
    private val input = InputReader.read<Int>(Day(10))

    override fun part1(): Int {
        return AdapterArray(input.value).getJoltageDelta()
    }

    override fun part2(): Long {
        return AdapterArray(input.value).getCombinations()
    }
}