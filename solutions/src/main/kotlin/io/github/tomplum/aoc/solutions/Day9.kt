package io.github.tomplum.aoc.solutions

import io.github.tomplum.aoc.Solution
import io.github.tomplum.aoc.security.XMASDecrypter
import io.github.tomplum.libs.input.Day
import io.github.tomplum.libs.input.InputReader

class Day9 : Solution<Long> {
    private val input = InputReader.read<String>(Day(9)).value.map { it.toLong() }

    override fun part1(): Long {
        return XMASDecrypter(input).decrypt(25)
    }

    override fun part2(): Long {
        val firstWeakness = XMASDecrypter(input).decrypt(25)
        return XMASDecrypter(input).discoverWeakness(firstWeakness)
    }
}