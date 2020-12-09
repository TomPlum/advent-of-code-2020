package io.github.tomplum.aoc.security

import io.github.tomplum.aoc.extensions.cartesianProductQuadratic
import io.github.tomplum.aoc.extensions.sum

class XMASDecrypter(private val data: List<Long>) {
    fun decrypt(preambleSize: Int): Long = data.asSequence()
        .windowed(preambleSize + 1).map { Pair(it.take(preambleSize), it.last()) }
        .mapNotNull { (preamble, candidate) ->
            val sums = preamble.pairCombinations().filter { pair -> pair.sum() == candidate }
            if (sums.isEmpty()) candidate else null
        }.first()

    fun discoverWeakness(firstWeakness: Long): Long {
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
        return range.minOrNull()!! + range.maxOrNull()!!
    }

    private fun List<Long>.pairCombinations() = cartesianProductQuadratic().filter { it.first != it.second }
}