package io.github.tomplum.aoc.solutions

import io.github.tomplum.aoc.Solution
import io.github.tomplum.aoc.airport.train.image.ImageBuilder
import io.github.tomplum.aoc.airport.train.image.ImageTileReader
import io.github.tomplum.libs.input.Day
import io.github.tomplum.libs.input.InputReader

class Day20 : Solution<Long, Int> {
    private val input = InputReader.read<String>(Day(20)).asSingleString()
    private val tiles = ImageTileReader.read(input)
    private val builder = ImageBuilder(tiles)

    override fun part1(): Long {
        return builder.getCornerTileIDProduct()
    }

    override fun part2(): Int {
        return builder.assemble().getHabitatWaterRoughness()
    }
}