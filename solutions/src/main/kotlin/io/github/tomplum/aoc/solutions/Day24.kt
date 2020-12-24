package io.github.tomplum.aoc.solutions

import io.github.tomplum.aoc.Solution
import io.github.tomplum.aoc.island.lobby.HexGrid
import io.github.tomplum.aoc.island.lobby.TileLayoutParser
import io.github.tomplum.libs.input.Day
import io.github.tomplum.libs.input.InputReader

class Day24 : Solution<Int, Int> {
    private val input = InputReader.read<String>(Day(24))

    override fun part1(): Int {
        val layout = TileLayoutParser.parse(input.value)
        return HexGrid(layout).getBlackTileCount()
    }
}