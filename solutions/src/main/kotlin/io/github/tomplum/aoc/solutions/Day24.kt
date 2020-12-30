package io.github.tomplum.aoc.solutions

import io.github.tomplum.aoc.Solution
import io.github.tomplum.aoc.island.lobby.ArtExhibitSimulator
import io.github.tomplum.aoc.island.lobby.LobbyFloor
import io.github.tomplum.aoc.island.lobby.TileLayoutParser
import io.github.tomplum.libs.input.Day
import io.github.tomplum.libs.input.InputReader

class Day24 : Solution<Int, Int> {
    private val input = InputReader.read<String>(Day(24))
    private val layout = TileLayoutParser.parse(input.value)
    private val lobbyFloor = LobbyFloor((layout))

    override fun part1(): Int {
        return lobbyFloor.getBlackTileCount()
    }

    override fun part2(): Int {
        return ArtExhibitSimulator(lobbyFloor).simulate(100)
    }
}