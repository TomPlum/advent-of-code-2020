package io.github.tomplum.aoc.aircraft.seating.strategy

import io.github.tomplum.aoc.aircraft.seating.SeatingLayout
import io.github.tomplum.libs.math.Point2D

interface SeatingStrategy {
    fun getOccupiedSeatPositions(layout: SeatingLayout): Set<Point2D>
    fun getEmptySeatPositions(layout: SeatingLayout): Set<Point2D>
}