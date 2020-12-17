package io.github.tomplum.aoc.aircraft.energy

import io.github.tomplum.libs.math.map.MapTile

class ConwayCube(private val state: Char): MapTile<Char>(state) {
    fun isActive(): Boolean = state == '#'

    fun isInActive(): Boolean = state == '.'
}