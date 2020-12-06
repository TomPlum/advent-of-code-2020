package io.github.tomplum.aoc

import kotlin.UnsupportedOperationException

/**
 * The solution to both parts of a given day in Advent of Code.
 */
interface Solution<T> {
    fun part1(): T? = null
    fun part2(): T? = null
}