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

    private fun List<Long>.newCartesianProduct() = cartesianProductQuadratic().filter { it.first != it.second }
}