package io.github.tomplum.aoc.train

import assertk.assertThat
import assertk.assertions.isEqualTo
import io.github.tomplum.aoc.input.TestInputReader
import org.junit.jupiter.api.Test

class TicketScannerTest {
    @Test
    fun example() {
        val input = TestInputReader.read<String>("train/notes/example.txt").asSingleString()
        val document = TicketReader.read(input)
        assertThat(TicketScanner().scan(document)).isEqualTo(71)
    }
}