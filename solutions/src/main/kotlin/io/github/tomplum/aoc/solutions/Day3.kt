package io.github.tomplum.aoc.solutions

import io.github.tomplum.aoc.Solution
import io.github.tomplum.aoc.map.ForestMap
import io.github.tomplum.aoc.map.SlopeTrajectory
import io.github.tomplum.libs.input.Day
import io.github.tomplum.libs.input.InputReader

class Day3 : Solution<Long> {
    private val data = InputReader.read<String>(Day(3))

    override fun part1(): Long {
        return ForestMap(data.value).trackTobogganTrajectory()
    }

    override fun part2(): Long {
        val slopes = listOf(SlopeTrajectory(1,1), SlopeTrajectory(3,1), SlopeTrajectory(5,1), SlopeTrajectory(7,1), SlopeTrajectory(1,2))
        return ForestMap(data.value).trackTobogganTrajectory(slopes)
    }
}