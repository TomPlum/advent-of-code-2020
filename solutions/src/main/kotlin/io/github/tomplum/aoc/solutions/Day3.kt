package io.github.tomplum.aoc.solutions

import io.github.tomplum.aoc.Solution
import io.github.tomplum.aoc.forest.toboggan.ForestMap
import io.github.tomplum.aoc.forest.toboggan.SlopeTrajectory
import io.github.tomplum.libs.input.Day
import io.github.tomplum.libs.input.InputReader

class Day3 : Solution<Int, Long> {
    private val data = InputReader.read<String>(Day(3))
    private val forestMap = ForestMap(data.value)

    override fun part1(): Int {
        return forestMap.trackTobogganTrajectory(SlopeTrajectory(3,1)).toInt()
    }

    override fun part2(): Long {
        val slopes = arrayOf(SlopeTrajectory(1,1), SlopeTrajectory(3,1), SlopeTrajectory(5,1), SlopeTrajectory(7,1), SlopeTrajectory(1,2))
        return forestMap.trackTobogganTrajectory(*slopes)
    }
}