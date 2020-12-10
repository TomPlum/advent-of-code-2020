package io.github.tomplum.aoc.aircraft.adapter

class AdapterChain(ratings: List<Long>) {
    private val adapters = ratings.asSequence()
        .plus(0)
        .plus(ratings.maxOrNull()!! + 3)
        .map { Adapter(it) }
        .sortedBy { it.rating }
        .toMutableList()

    fun getJoltageDelta(): Long = adapters
        .zipWithNext { a, b -> a.ratingDifference(b) }
        .let { diff -> diff.count { it == 1L } * diff.count { it == 3L } }
        .toLong()

    fun getCombinations(): Long = adapters.map { it.rating }.drop(1).fold(mutableMapOf(0L to 1L)) { acc, rating ->
        acc.apply { put(rating, (1L..3L).map { delta -> acc.getOrDefault(rating - delta, 0L) }.sum()) }
    }.let { it[adapters.last().rating] } ?: throw IllegalStateException("No Built-In Outlet Rating State")

}