package io.github.tomplum.aoc.train

import assertk.assertThat
import assertk.assertions.isEqualTo
import io.github.tomplum.aoc.input.TestInputReader
import io.github.tomplum.aoc.train.ticket.TicketReader
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class TicketScannerTest {
    @Nested
    inner class GetErrorScanningRate {
        @Test
        fun example() {
            val input = TestInputReader.read<String>("train/notes/example.txt").asSingleString()
            val document = TicketReader.read(input)
            assertThat(TicketScanner(document).getErrorRate()).isEqualTo(71)
        }
    }

    @Nested
    inner class Scan {
        @Test
        fun example() {
            val input = TestInputReader.read<String>("train/notes/example-2.txt").asSingleString()
            val document = TicketReader.read(input)
            assertThat(TicketScanner(document).scan("class")).isEqualTo(12)
        }
    }
}