package io.github.tomplum.aoc.map

import io.github.tomplum.libs.map.MapTile

class ForestTile(value: Char) : MapTile<Char>(value) {
    fun isTree(): Boolean = value == '#'
    fun isEmpty(): Boolean = value == '.'
}