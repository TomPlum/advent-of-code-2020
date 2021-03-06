package io.github.tomplum.aoc.island.lobby

import io.github.tomplum.libs.math.Direction

/**
 * A member of the renovation crew gives you a list of the tiles that need to be flipped over.
 * This class parses these instructions and produces a list of sub-lists of the directions from the reference tile.
 */
class TileLayoutParser private constructor() {
    companion object {
        fun parse(data: List<String>): List<List<Direction>> = data.map { line ->
            var isDiagonal = false
            line.mapIndexedNotNull { i, char ->
                if (!isDiagonal) {
                    when (char) {
                        'n' -> {
                            isDiagonal = true
                            when (val northDiagonal = "$char${line[i + 1]}") {
                                "ne" -> Direction.TOP_RIGHT
                                "nw" -> Direction.TOP_LEFT
                                else -> throw IllegalArgumentException("$northDiagonal is not a valid direction.")
                            }
                        }
                        's' -> {
                            isDiagonal = true
                            when (val southDiagonal = "$char${line[i + 1]}") {
                                "se" -> Direction.BOTTOM_RIGHT
                                "sw" -> Direction.BOTTOM_LEFT
                                else -> throw IllegalArgumentException("$southDiagonal is not a valid direction.")
                            }
                        }
                        'e' -> Direction.RIGHT
                        'w' -> Direction.LEFT
                        else -> throw IllegalArgumentException("$char is not a valid direction.")
                    }
                } else {
                    isDiagonal = false
                    null
                }
            }
        }
    }
}