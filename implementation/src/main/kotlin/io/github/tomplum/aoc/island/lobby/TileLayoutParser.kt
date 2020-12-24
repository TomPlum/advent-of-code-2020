package io.github.tomplum.aoc.island.lobby

import io.github.tomplum.libs.math.Direction

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
                                else -> throw IllegalStateException("$northDiagonal is not a valid direction.")
                            }
                        }
                        's' -> {
                            isDiagonal = true
                            when (val southDiagonal = "$char${line[i + 1]}") {
                                "se" -> Direction.BOTTOM_RIGHT
                                "sw" -> Direction.BOTTOM_LEFT
                                else -> throw IllegalStateException("$southDiagonal is not a valid direction.")
                            }
                        }
                        'e' -> Direction.RIGHT
                        'w' -> Direction.LEFT
                        else -> throw IllegalArgumentException("$char is not a valid direction")
                    }
                } else {
                    isDiagonal = false
                    null
                }
            }
        }
    }
}