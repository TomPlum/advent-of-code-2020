package io.tomplum.aoc

/**
 * The solution to both parts of a given day in Advent of Code.
 */
interface Solution<T> {
    fun part1(): T
    fun part2(): T
}