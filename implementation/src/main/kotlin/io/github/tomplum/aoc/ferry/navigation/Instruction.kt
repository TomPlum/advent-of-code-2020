package io.github.tomplum.aoc.ferry.navigation

import io.github.tomplum.libs.math.Direction

/**
 * A single instruction used by a [NavigationSystem].
 *
 * @param action An action of type [Directive] or [Direction].
 * @param value The number of units to move or degrees to rotate.
 */
data class Instruction<T>(val action: T, val value: Int)