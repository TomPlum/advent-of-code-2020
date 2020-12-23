package io.github.tomplum.aoc.raft.cups

import io.github.tomplum.aoc.airport.game.MemoryGame

/**
 * A circle of cups maintained by the crab running the [CrabCupGame].
 *
 * The backing collection for this pseudo-circular data structure is an [IntArray].
 * It works similar to the [MemoryGame] with regards to the use of both the index and the value of the array.
 *
 * In this case, [values] stores the label of the cup as the index, and the label of its clockwise cup as its value.
 * Because cups labels start from 1, we simply initialise the array with an extra element and pre-allocate -1.
 * This eliminates the need to 'shift' around values whenever a cups position changes.
 *
 * @param cups The ordered values of the cups.
 */
class CupCircle(cups: List<Int>) {

    val startingCup = cups.first().toString().toInt()
    private val values: IntArray = IntArray(cups.size + 1) { -1 }

    init {
        cups.zipWithNext { a, b -> values[a] = b }
        values[cups.last()] = cups.first()
    }

    /**
     * Gets the values of the three cups immediately clockwise of the [current] cup.
     * @param current The value of the cup of which to pick clockwise from.
     * @return The values of the three clockwise cups, in order.
     */
    fun pickClockwiseCups(current: Int): List<Int> {
        val first = values[current]
        val second = values[first]
        val third = values[second]
        return listOf(first, second, third)
    }

    /**
     * Gets the value of the cup immediately clockwise of the [current] cup.
     * @param current The value of the cup of which to pick clockwise from.
     * @return The value of the picked cup.
     */
    fun getClockwiseCup(current: Int): Int {
        return values[current]
    }

    /**
     * Places the given [cups] immediately clockwise of the [destination] cup, while preserving their order.
     * @param currentCup The current cup picked by the crab.
     * @param cups The three cups recently picked by the crab.
     * @param destination The destination cup where the [cups] will be placed relative to.
     */
    fun place(currentCup: Int, cups: List<Int>, destination: Int) {
        val clockwiseDestination = values[destination]
        values[currentCup] = values[cups.last()]
        values[destination] = cups.first()
        values[cups.last()] = clockwiseDestination
    }

    /**
     * Gets the destination cup based on the rules defined in the [CrabCupGame].
     * @param picked The three cups recently picked by the crab.
     * @param currentCup The current cup picked by the crab.
     * @return The value of the destination cup.
     */
    fun getDestinationCup(picked: List<Int>, currentCup: Int): Int {
        var candidate = currentCup - 1
        val smallestCupValue = 1
        while (picked.contains(candidate) || candidate < smallestCupValue) {
            if (candidate < smallestCupValue) {
                candidate = values.size - 1
            } else {
                candidate--
            }
        }
        return candidate
    }

    /**
     * Gets the order of the cups in their current state, excluding the cup with value 1.
     * @return A string of the cup values in order.
     */
    fun getCupOrder(): String {
        val order = StringBuilder()
        (2 until values.size).fold(1) { acc, _ ->
            val next = values[acc]
            order.append(next)
            next
        }
        return order.toString()
    }
}