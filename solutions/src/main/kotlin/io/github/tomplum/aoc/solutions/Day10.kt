package io.github.tomplum.aoc.solutions

import io.github.tomplum.aoc.Solution
import io.github.tomplum.aoc.aircraft.adapter.AdapterChain
import io.github.tomplum.libs.input.Day
import io.github.tomplum.libs.input.InputReader

class Day10 : Solution<Int> {
    override fun part1(): Int {
        val input = InputReader.read<Int>(Day(10))
        val adapterChain = AdapterChain(input.value)
        return adapterChain.getJoltageDelta()
    }
}