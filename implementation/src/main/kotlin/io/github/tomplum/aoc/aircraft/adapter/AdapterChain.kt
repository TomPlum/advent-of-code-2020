package io.github.tomplum.aoc.aircraft.adapter

class AdapterChain(ratings: List<Int>) {
    private val adapters = ratings.asSequence()
        .plus(0)
        .plus(ratings.maxOrNull()!! + 3)
        .map { Adapter(it) }
        .sortedBy { it.rating }
        .toMutableList()

    fun getJoltageDelta(): Int = adapters
        .zipWithNext { a, b -> a.ratingDifference(b) }
        .let { diff ->
            diff.count { it == 1 } * diff.count { it == 3 }
        }

    fun getCombinations(): Int = adapters.map { it.rating }.drop(1).fold(mutableMapOf(0 to 1)) { acc, rating ->
        acc.apply { put(rating, (1..3).map { delta -> acc.getOrDefault(rating - delta, 0) }.sum()) }
    }.let { it[adapters.last().rating] } ?: throw IllegalStateException("No Built-In Outlet Rating State")

}