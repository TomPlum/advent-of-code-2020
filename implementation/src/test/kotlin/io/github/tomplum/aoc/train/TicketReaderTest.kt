package io.github.tomplum.aoc.train

import assertk.assertThat
import assertk.assertions.isEqualTo
import io.github.tomplum.aoc.input.TestInputReader
import org.junit.jupiter.api.Test

class TicketReaderTest {
    @Test
    fun example() {
        val input = TestInputReader.read<String>("train/notes/example.txt").asSingleString()
        val document = TicketReader.read(input)
        assertThat(document).isEqualTo(getExpectedDocument())
    }

    private fun getExpectedDocument(): TrainServiceDocument {
        return TrainServiceDocument(
            Ticket(listOf(7, 1, 14)),
            listOf(
                Ticket(listOf(7, 3, 47)),
                Ticket(listOf(40, 4, 50)),
                Ticket(listOf(55, 2, 20)),
                Ticket(listOf(38, 6, 12))
            ),
            listOf(
                TicketRule("class", 1..3, 5..7),
                TicketRule("row", 6..11, 33..44),
                TicketRule("seat", 13..40, 45..50)
            )
        )
    }
}
