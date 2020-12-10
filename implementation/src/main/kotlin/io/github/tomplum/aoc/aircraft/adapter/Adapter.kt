package io.github.tomplum.aoc.aircraft.adapter

import kotlin.math.abs

data class Adapter(val rating: Int) {
    fun ratingDifference(other: Adapter): Int = other.rating - rating

    fun isCompatible(other: Adapter): Boolean = abs(other.rating - rating) <= 3
}