package io.github.tomplum.aoc.solutions

import io.github.tomplum.aoc.Solution
import io.github.tomplum.aoc.airport.train.image.ImageReader
import io.github.tomplum.libs.input.Day
import io.github.tomplum.libs.input.InputReader

class Day20 : Solution<Long, Long> {
    private val input = InputReader.read<String>(Day(20)).asSingleString()

    override fun part1(): Long {
        return ImageReader.read(input).assemble()
    }
}