package io.github.tomplum.aoc.solutions

import io.github.tomplum.aoc.Solution
import io.github.tomplum.aoc.security.XMASDecrypter
import io.github.tomplum.libs.input.Day
import io.github.tomplum.libs.input.InputReader

class Day9 : Solution<Long> {
    override fun part1(): Long {
        val input = InputReader.read<String>(Day(9)).value.map { it.toLong() }
        val decrypter = XMASDecrypter(input)
        return decrypter.decrypt(25)
    }
}