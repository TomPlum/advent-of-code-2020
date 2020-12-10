package io.github.tomplum.aoc.aircraft.adapter

import kotlin.math.abs

data class Adapter(val rating: Long) {
    fun ratingDifference(other: Adapter): Long = other.rating - rating

    fun isCompatible(other: Adapter): Boolean = abs(other.rating - rating) <= 3
}