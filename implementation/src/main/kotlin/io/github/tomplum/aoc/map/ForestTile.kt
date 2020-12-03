package io.github.tomplum.aoc.map

import io.github.tomplum.libs.map.MapTile

/**
 * A single tile in a [ForestMap].
 */
class ForestTile(value: Char) : MapTile<Char>(value) {
    fun isTree(): Boolean = value == '#'
    fun isEmpty(): Boolean = value == '.'
}