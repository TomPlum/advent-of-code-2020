package io.github.tomplum.aoc.aircraft.adapter

/**
 * An array adapters that can be used to connect to the charging outlet on the aircraft.
 *
 * Each of the joltage adapters is rated for a specific output joltage.
 * Any given adapter can take an input 1, 2, or 3 jolts lower than its rating and still produce its rated output joltage.
 *
 * In addition, your device has a built-in joltage adapter rated for 3 jolts higher than the highest-rated adapter.
 *
 * The charging outlet near the seat has an effective joltage rating of 0.
 *
 * @param ratings A list of the ratings of each of the adapters in your bag.
 */
class AdapterArray(ratings: List<Int>) {
    private val adapters = ratings
        .plus(0) //Outlet
        .plus(ratings.maxOrNull()!! + 3) //Device Rating
        .sorted()

    /**
     * Calculates the joltage delta between all the adapters in your bag. Each adapter is only used once.
     * @return The product of the lowest and highest adapter joltage deltas.
     */
    fun getJoltageDelta(): Int = adapters
        .zipWithNext { a, b -> b - a }
        .let { delta -> delta.count { it == 1 } * delta.count { it == 3 } }

    /**
     * Calculates the total number combinations of adapters that can provide the necessary output joltage to charge.
     */
    fun getCombinations(): Long = adapters.drop(1).fold(mutableMapOf(0 to 1L)) { acc, rating ->
        acc.apply { put(rating, (1..3).map { delta -> acc.getOrDefault(rating - delta, 0L) }.sum()) }
    }.getOrDefault(adapters.last(), 0)

}