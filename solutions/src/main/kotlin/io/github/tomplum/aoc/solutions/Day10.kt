package io.github.tomplum.aoc.solutions

import io.github.tomplum.aoc.Solution
import io.github.tomplum.aoc.aircraft.adapter.AdapterChain
import io.github.tomplum.libs.input.Day
import io.github.tomplum.libs.input.InputReader

class Day10 : Solution<Long> {
    private val input = InputReader.read<Long>(Day(10))

    override fun part1(): Long {
        return AdapterChain(input.value).getJoltageDelta()
    }

    override fun part2(): Long {
        return AdapterChain(input.value).getCombinations()
    }
}