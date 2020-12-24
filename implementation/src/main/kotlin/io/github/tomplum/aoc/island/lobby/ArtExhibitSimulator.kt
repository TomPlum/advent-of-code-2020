package io.github.tomplum.aoc.island.lobby

import io.github.tomplum.libs.logging.AdventLogger

class ArtExhibitSimulator(private val initialFloorLayout: LobbyFloor) {
    fun simulate(days: Int): Int {
        var state = initialFloorLayout.getFloorSnapshot()

        repeat(days) { day ->
            val nextBlack = state.getNextBlackTiles()
            val nextWhite = state.getNextWhiteTiles()

            state.flip(nextWhite + nextBlack)

            AdventLogger.info("Day ${day + 1}: ${state.getBlackTileCount()}")

            state = state.getFloorSnapshot()
        }

        return state.getBlackTileCount()
    }
}