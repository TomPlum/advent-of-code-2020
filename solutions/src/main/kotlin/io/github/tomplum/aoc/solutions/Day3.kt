package io.github.tomplum.aoc.solutions

import io.github.tomplum.aoc.Solution
import io.github.tomplum.aoc.map.ForestMap
import io.github.tomplum.libs.input.Day
import io.github.tomplum.libs.input.InputReader

class Day3 : Solution<Int> {
    override fun part1(): Int {
        val data = InputReader.read<String>(Day(3))
        val map = ForestMap(data.value)
        return map.trackTobogganTrajectory()
    }
}