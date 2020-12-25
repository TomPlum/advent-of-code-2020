package io.github.tomplum.aoc.island.lobby

import io.github.tomplum.libs.logging.AdventLogger

/**
 * The tile floor in the lobby is meant to be a living art exhibit.
 *
 * Every day, the tiles are all flipped according to the following rules:
 *
 * - Any black tile with zero or more than 2 black tiles immediately adjacent to it is flipped to white.
 * - Any white tile with exactly 2 black tiles immediately adjacent to it is flipped to black.
 *
 * @param initialFloorLayout The initial state of the tiles
 */
class ArtExhibitSimulator(private val initialFloorLayout: LobbyFloor) {
    /**
     * Simulates the living art exhibit for the given number of [days].
     * @param days The number of days to simulate.
     * @return The number of black tiles after the simulation is complete.
     */
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