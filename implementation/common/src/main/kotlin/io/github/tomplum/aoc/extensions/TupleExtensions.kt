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

/**
 * Calculates the sum of the values of a given [Triple].
 * @return The sum of the [Triple.first], [Triple.second] and [Triple.third] values.
 */
fun Triple<Int, Int, Int>.sum() = this.first + this.second + this.third

/**
 * Calculates the product of the values of a given [Triple].
 * @return The product of the [Triple.first], [Triple.second] and [Triple.third] values.
 */
fun Triple<Int, Int, Int>.product() = this.first * this.second * this.third