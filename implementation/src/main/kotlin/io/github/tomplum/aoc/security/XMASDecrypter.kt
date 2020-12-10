package io.github.tomplum.aoc.security

import io.github.tomplum.aoc.extensions.cartesianProductQuadratic
import io.github.tomplum.aoc.extensions.sum

/**
 * The mini-display on the plane as an open data port. The port outputs a series of numbers which are encrypted with
 * "The eXchange-Masking Addition System (XMAS)". This is an old cypher and has weaknesses.
 *
 * XMAS starts by transmitting a preamble. After that, each number output should be the sum of any 2 numbers in the
 * last preamble window. The two numbers must have different values and there might be more that one such pair.
 *
 * For example; if the preamble is N, then each number output O should have a pair of numbers in the last N numbers
 * before it that sum to O.
 *
 * @param data The initial stream of numbers output by the port once connected.
 */
class XMASDecrypter(private val data: List<Long>) {
    /**
     * Attacks the weakness in the XMAS [data] and finds the numbers that are not the sum of two numbers in the
     * [preambleSize] numbers before it.
     *
     * @return The first number encountered.
     */
    fun decrypt(preambleSize: Int): Long = data.asSequence()
        .windowed(preambleSize + 1).map { Pair(it.take(preambleSize), it.last()) }
        .mapNotNull { (preamble, candidate) ->
            val sums = preamble.pairCombinations().filter { pair -> pair.sum() == candidate }
            if (sums.isEmpty()) candidate else null
        }.first()

    /**
     * The final step in breaking the XMAS encryption relies on the invalid number found by [decrypt].
     * Finds a contiguous set of at least two numbers in the [data] that sum to the value of the [firstWeakness].
     * @param firstWeakness The invalid value found by invoking [decrypt].
     * @return The sum of the first and last values in the contiguous array.
     */
    fun discoverWeakness(firstWeakness: Long): Int {
        var windowFloor = 0
        var windowCeiling = 0
        var sum = 0L
        while (sum != firstWeakness) {
            if (sum > firstWeakness) {
                sum = 0
                windowFloor++
                windowCeiling = windowFloor
            } else {
                sum += data[windowCeiling]
                windowCeiling++
            }
        }
        val range = data.subList(windowFloor, windowCeiling)
        return (range.minOrNull()!! + range.maxOrNull()!!).toInt()
    }

    private fun List<Long>.pairCombinations() = cartesianProductQuadratic().filter { it.first != it.second }
}