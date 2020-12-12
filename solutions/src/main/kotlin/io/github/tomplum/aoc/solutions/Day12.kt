package io.github.tomplum.aoc.solutions

import io.github.tomplum.aoc.Solution
import io.github.tomplum.aoc.ferry.navigation.NavigationSystem
import io.github.tomplum.libs.input.Day
import io.github.tomplum.libs.input.InputReader

class Day12 : Solution<Int, Int> {
    private val input = InputReader.read<String>(Day(12))

    override fun part1(): Int {
        return NavigationSystem(input.value).navigate()
    }

    override fun part2(): Int {
        return NavigationSystem(input.value).navigateViaWaypoint()
    }
}