package io.github.tomplum.aoc.solutions

import io.github.tomplum.aoc.Solution
import io.github.tomplum.aoc.airport.train.image.ImageReader
import io.github.tomplum.libs.input.Day
import io.github.tomplum.libs.input.InputReader

class Day20 : Solution<Long, Int> {
    private val input = InputReader.read<String>(Day(20)).asSingleString()
    private val imageBuilder = ImageReader.read(input)

    override fun part1(): Long {
        return imageBuilder.getCornerTileIDProduct()
    }

    override fun part2(): Int {
        return imageBuilder.assemble().getHabitatWaterRoughness()
    }
}