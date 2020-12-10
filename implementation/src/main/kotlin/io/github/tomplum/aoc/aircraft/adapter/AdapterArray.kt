package io.github.tomplum.aoc.aircraft.adapter

class AdapterArray(ratings: List<Long>) {
    private val adapters = ratings
        .plus(0) //Outlet
        .plus(ratings.maxOrNull()!! + 3) //Device Rating
        .sorted()

    fun getJoltageDelta(): Long = adapters
        .zipWithNext { a, b -> b - a }
        .let { delta -> delta.count { it == 1L } * delta.count { it == 3L } }
        .toLong()

    fun getCombinations(): Long = adapters.drop(1).fold(mutableMapOf(0L to 1L)) { acc, rating ->
        acc.apply { put(rating, (1L..3L).map { delta -> acc.getOrDefault(rating - delta, 0L) }.sum()) }
    }.getOrDefault(adapters.last(), 0)

}