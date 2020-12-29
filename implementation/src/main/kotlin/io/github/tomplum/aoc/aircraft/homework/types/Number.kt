package io.github.tomplum.aoc.aircraft.homework.types

/**
 * A single 64bit signed integer in an [Expression].
 * @param value The value of the number.
 */
data class Number(val value: Long): Token {
    /**
     * The length of a number with respect to its status
     * as a [Token] in an [Expression] is always 1.
     */
    override fun getLength(): Int = 1

    override fun toString(): String = value.toString()
}