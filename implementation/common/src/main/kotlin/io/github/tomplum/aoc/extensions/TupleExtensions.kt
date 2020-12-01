package io.github.tomplum.aoc.extensions

/**
 * Calculates the product of the values of a given [Pair].
 * @return The product of [Pair.first] and [Pair.second].
 */
fun Pair<Int, Int>.product() = this.first * this.second

/**
 * Calculates the sum of the values of a given [Pair].
 * @return The sum of [Pair.first] and [Pair.second].
 */
fun Pair<Int, Int>.sum() = this.first + this.second