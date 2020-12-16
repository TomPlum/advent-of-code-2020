package io.github.tomplum.aoc.train.ticket

import assertk.assertThat
import assertk.assertions.containsOnly
import assertk.assertions.isEmpty
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class TicketTest {
    @Nested
    inner class GetFields {
        @Test
        fun hasFieldWithExactTermName() {
            val ticket = Ticket(mapOf(Pair("carriage", 23)))
            val fieldValues = ticket.getFields("carriage")
            assertThat(fieldValues).containsOnly(23)
        }

        @Test
        fun hasFieldContainingTerm() {
            val ticket = Ticket(mapOf(Pair("departure time", 15)))
            val fieldValues = ticket.getFields("departure")
            assertThat(fieldValues).containsOnly(15)
        }

        @Test
        fun hasMultipleFieldsContainingTerm() {
            val ticket = Ticket(mapOf(Pair("departure time", 15), Pair("departure date", 24)))
            val fieldValues = ticket.getFields("departure")
            assertThat(fieldValues).containsOnly(15, 24)
        }

        @Test
        fun hasFieldContainingTermAndFieldThatDoesNot() {
            val ticket = Ticket(mapOf(Pair("departure time", 12), Pair("waggon", 24)))
            val fieldValues = ticket.getFields("departure")
            assertThat(fieldValues).containsOnly(12)
        }

        @Test
        fun hasNoFieldsContainingTerm() {
            assertThat(Ticket(emptyMap()).getFields("test")).isEmpty()
        }
    }
}