package io.github.tomplum.aoc.aircraft.energy

import io.github.tomplum.libs.math.map.MapTile

/**
 * A single cube in a [PocketDimension].
 * @param state The boolean state of the cube. It is either active, or inactive.
 */
class ConwayCube(private val state: Char): MapTile<Char>(state) {
    /**
     * Checks to the see if the cube is in an active state.
     * @return True if active, else false.
     */
    fun isActive(): Boolean = state == '#'

    /**
     * Checks to the see if the cube is an in-active state.
     * @return true if in-active, else false.
     */
    fun isInActive(): Boolean = state == '.'

    companion object {
        fun active() = ConwayCube('#')
        fun inactive() = ConwayCube('.')
    }
}