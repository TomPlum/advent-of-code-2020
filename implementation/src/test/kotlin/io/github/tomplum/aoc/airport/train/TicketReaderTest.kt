package io.github.tomplum.aoc.airport.train

import assertk.assertThat
import assertk.assertions.isEqualTo
import io.github.tomplum.aoc.input.TestInputReader
import io.github.tomplum.aoc.airport.train.ticket.EncodedTicket
import io.github.tomplum.aoc.airport.train.ticket.TicketReader
import io.github.tomplum.aoc.airport.train.ticket.TicketRule
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
            EncodedTicket(listOf(7, 1, 14)),
            listOf(
                EncodedTicket(listOf(7, 3, 47)),
                EncodedTicket(listOf(40, 4, 50)),
                EncodedTicket(listOf(55, 2, 20)),
                EncodedTicket(listOf(38, 6, 12))
            ),
            listOf(
                TicketRule("class", 1..3, 5..7),
                TicketRule("row", 6..11, 33..44),
                TicketRule("seat", 13..40, 45..50)
            )
        )
    }
}
