package io.github.tomplum.aoc.solutions

import io.github.tomplum.aoc.Solution
import io.github.tomplum.aoc.island.room.Door
import io.github.tomplum.aoc.island.room.HandshakeDecrypter
import io.github.tomplum.aoc.island.room.KeyCard
import io.github.tomplum.libs.input.Day
import io.github.tomplum.libs.input.InputReader

class Day25: Solution<Long, Long> {
    private val input = InputReader.read<Long>(Day(25)).value

    override fun part1(): Long {
        val devices = Pair(KeyCard(input[0]), Door(input[1]))
        return HandshakeDecrypter(devices).getEncryptionKey()
    }
}