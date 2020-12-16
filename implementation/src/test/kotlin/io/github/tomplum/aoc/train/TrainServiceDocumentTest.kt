package io.github.tomplum.aoc.train

import assertk.assertThat
import assertk.assertions.containsOnly
import assertk.assertions.isEmpty
import io.github.tomplum.aoc.input.TestInputReader
import io.github.tomplum.aoc.train.ticket.EncodedTicket
import io.github.tomplum.aoc.train.ticket.TicketReader
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class TrainServiceDocumentTest {
    @Nested
    inner class GetInvalidTicketValues {
        @Test
        fun exampleOne() {
            val notes = TestInputReader.read<String>("train/notes/example.txt").asSingleString()
            val document = TicketReader.read(notes)
            assertThat(document.getInvalidTicketValues()).containsOnly(4, 12, 55)
        }

        @Test
        fun exampleTwo() {
            val notes = TestInputReader.read<String>("train/notes/example-2.txt").asSingleString()
            val document = TicketReader.read(notes)
            assertThat(document.getInvalidTicketValues()).isEmpty()
        }
    }

    @Nested
    inner class GetValidTickets {
        @Test
        fun exampleOne() {
            val notes = TestInputReader.read<String>("train/notes/example.txt").asSingleString()
            val document = TicketReader.read(notes)
            assertThat(document.getValidTickets()).containsOnly(EncodedTicket(listOf(7, 3, 47)))
        }

        @Test
        fun exampleTwo() {
            val notes = TestInputReader.read<String>("train/notes/example-2.txt").asSingleString()
            val document = TicketReader.read(notes)
            assertThat(document.getValidTickets()).containsOnly(
                EncodedTicket(listOf(3, 9, 18)), EncodedTicket(listOf(15, 1, 5)), EncodedTicket(listOf(5, 14, 9))
            )
        }
    }
}