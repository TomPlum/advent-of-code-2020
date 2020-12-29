package io.github.tomplum.aoc.airport.train.ticket

import assertk.assertThat
import assertk.assertions.isEqualTo
import assertk.assertions.isFalse
import assertk.assertions.isTrue
import io.github.tomplum.aoc.airport.train.ticket.meta.TicketMeta
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class EncodedTicketTest {
    @Nested
    inner class IsValid {
        private val rules =  listOf(
            TicketRule("class", 1..3, 5..7),
            TicketRule("row", 6..11, 33..44),
            TicketRule("seat", 13..40, 45..50)
        )

        @Test
        fun exampleOne() {
            assertThat(EncodedTicket(listOf(7, 3, 47)).isValid(rules)).isTrue()
        }

        @Test
        fun exampleTwo() {
            assertThat(EncodedTicket(listOf(40, 4, 50)).isValid(rules)).isFalse()
        }

        @Test
        fun exampleThree() {
            assertThat(EncodedTicket(listOf(55, 2, 20)).isValid(rules)).isFalse()
        }

        @Test
        fun exampleFour() {
            assertThat(EncodedTicket(listOf(38, 6, 12)).isValid(rules)).isFalse()
        }
    }

    @Nested
    inner class Decode {
        private val rules = listOf(
            TicketRule("class", 0..1, 4..19),
            TicketRule("row", 0..5, 8..19),
            TicketRule("seat", 0..13, 16..19)
        )

        @Test
        fun example() {
            val encodedTicket = EncodedTicket(listOf(11, 12, 13))
            val decodedTicket = encodedTicket.decode(getMeta())
            assertThat(decodedTicket).isEqualTo(getExpectedDecodedTicket())
        }

        private fun getExpectedDecodedTicket(): Ticket {
            val data = mutableMapOf<String, Int>()
            data["row"] = 11
            data["class"] = 12
            data["seat"] = 13
            return Ticket(data)
        }

        private fun getMeta(): TicketMeta {
            val meta = TicketMeta()
            meta.addRule(0, rules[1])
            meta.addRule(1, rules[0])
            meta.addRule(2, rules[2])
            return meta
        }
    }
}