package io.github.tomplum.aoc.island.lobby

import io.github.tomplum.libs.logging.AdventLogger

class ArtExhibitSimulator(private val initialFloorLayout: HexGrid) {
    fun simulate(days: Int): Int {
        var state = initialFloorLayout

        repeat(days) { day ->
            val nextBlack = state.getNextBlackTiles()
            val nextWhite = state.getNextWhiteTiles()

            state.flipBlack(nextWhite)
            state.flipWhite(nextBlack)

            AdventLogger.info("Day ${day + 1}: ${state.getBlackTileCount()}")

            state = state.getFloorSnapshot()
        }

        return state.getBlackTileCount()
    }
}