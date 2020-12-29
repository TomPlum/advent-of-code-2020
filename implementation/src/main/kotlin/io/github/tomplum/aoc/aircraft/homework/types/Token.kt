package io.github.tomplum.aoc.aircraft.homework.types

/**
 * A single term, operator or sub-expression in an [Expression]
 */
interface Token {
    fun getLength(): Int
}