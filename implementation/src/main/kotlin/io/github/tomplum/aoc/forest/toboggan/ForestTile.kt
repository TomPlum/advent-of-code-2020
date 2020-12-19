package io.github.tomplum.aoc.forest.toboggan

import io.github.tomplum.libs.math.map.MapTile

/**
 * A single tile in a [ForestMap].
 */
class ForestTile(value: Char) : MapTile<Char>(value) {
    fun isTree(): Boolean = value == '#'
    fun isEmpty(): Boolean = value == '.'
}