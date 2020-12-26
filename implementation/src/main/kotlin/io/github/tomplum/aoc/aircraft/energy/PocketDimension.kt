package io.github.tomplum.aoc.aircraft.energy

import io.github.tomplum.libs.math.point.Point

/**
 * A space-time dimensions containing an infinite array of [ConwayCube].
 */
interface PocketDimension {
    fun getNextActiveCubes(): List<Point>
    fun getNextInActiveCubes(): List<Point>
    fun activate(positions: List<Point>)
    fun deactivate(positions: List<Point>)
    fun getSnapshot(): PocketDimension
    fun getActiveCubeQuantity(): Int
}