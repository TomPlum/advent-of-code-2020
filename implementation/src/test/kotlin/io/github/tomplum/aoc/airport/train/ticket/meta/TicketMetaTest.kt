package io.github.tomplum.aoc.airport.train.ticket.meta

import assertk.assertThat
import assertk.assertions.isEqualTo
import io.github.tomplum.aoc.airport.train.ticket.TicketRule
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class TicketMetaTest {
    @Nested
    inner class AddRule {
        @Test
        fun example() {
            val meta = TicketMeta()
            meta.addRule(1, TicketRule("wagon", 0..2, 3..5))
            assertThat(meta.get(1)).isEqualTo(TicketRule("wagon", 0..2, 3..5))
        }
    }

    @Nested
    inner class GetRule {
        @Test
        fun getExistingRule() {
            val meta = TicketMeta()
            meta.addRule(1, TicketRule("carriage", 0..2, 3..5))
            assertThat(meta.get(1)).isEqualTo(TicketRule("carriage", 0..2, 3..5))
        }

        @Test
        fun getNonExistentRule() {
            val e = assertThrows<IllegalArgumentException> { TicketMeta().get(1) }
            assertThat(e.message).isEqualTo("No rule defined for column: 1")
        }
    }
}