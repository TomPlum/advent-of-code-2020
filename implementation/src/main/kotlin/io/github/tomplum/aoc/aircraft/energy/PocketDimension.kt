package io.github.tomplum.aoc.aircraft.energy

import io.github.tomplum.libs.math.point.Point

interface PocketDimension {
    fun getNextActiveCubes(): List<Point>
    fun getNextInActiveCubes(): List<Point>
    fun activate(positions: List<Point>)
    fun deactivate(positions: List<Point>)
    fun getSnapshot(): PocketDimension
    fun getActiveCubeQuantity(): Int
}