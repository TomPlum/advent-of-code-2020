package io.github.tomplum.aoc.aircraft.adapter

class AdapterArray(ratings: List<Int>) {
    private val adapters = ratings
        .plus(0) //Outlet
        .plus(ratings.maxOrNull()!! + 3) //Device Rating
        .sorted()

    fun getJoltageDelta(): Int = adapters
        .zipWithNext { a, b -> b - a }
        .let { delta -> delta.count { it == 1 } * delta.count { it == 3 } }

    fun getCombinations(): Long = adapters.drop(1).fold(mutableMapOf(0 to 1L)) { acc, rating ->
        acc.apply { put(rating, (1..3).map { delta -> acc.getOrDefault(rating - delta, 0L) }.sum()) }
    }.getOrDefault(adapters.last(), 0)

}