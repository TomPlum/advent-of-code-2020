package io.github.tomplum.aoc.solutions

import io.github.tomplum.aoc.Solution
import io.github.tomplum.aoc.island.room.Decrypter
import io.github.tomplum.libs.input.Day
import io.github.tomplum.libs.input.InputReader

class Day25: Solution<Long, Long> {
    private val input = InputReader.read<Int>(Day(25)).value

    override fun part1(): Long {
        return Decrypter().getEncryptionKey(input[0], input[1])
    }
}