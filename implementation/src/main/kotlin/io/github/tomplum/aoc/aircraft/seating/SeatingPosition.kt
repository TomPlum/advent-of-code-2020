package io.github.tomplum.aoc.aircraft.seating

import io.github.tomplum.libs.math.map.MapTile

class SeatingPosition(val status: Char): MapTile<Char>(status) {
    fun isFloor(): Boolean = status == '.'

    fun isEmpty(): Boolean = status == 'L'

    fun isOccupied(): Boolean = status == '#'
}