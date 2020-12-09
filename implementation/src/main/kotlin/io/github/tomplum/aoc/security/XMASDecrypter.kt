package io.github.tomplum.aoc.security

import io.github.tomplum.aoc.extensions.cartesianProductQuadratic
import io.github.tomplum.aoc.extensions.sum

class XMASDecrypter(private val data: List<Long>) {
    fun decrypt(preambleSize: Int): Long = data.windowed(preambleSize + 1) {
        val preamble = it.take(preambleSize)
        val candidate = it.last()
        val sums = preamble.newCartesianProduct().filter { pair -> pair.sum() == candidate }
        if (sums.isEmpty()) candidate else null
    }.filterNotNull().first()

    fun discoverWeakness(firstWeakness: Long): Long {
        var i = 0
        var j = 0
        var sum = 0L
        while (sum != firstWeakness) {
            if (sum > firstWeakness) {
                sum = 0
                i++
                j = i
            } else {
                sum += data[j]
                j++
            }
        }
        val range = data.subList(i, j)
        return range.minOrNull()!! + range.maxOrNull()!!
    }

    private fun List<Long>.newCartesianProduct() = cartesianProductQuadratic().filter { it.first != it.second }
}