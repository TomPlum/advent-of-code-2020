package io.github.tomplum.aoc.train.ticket.meta

import assertk.assertThat
import assertk.assertions.isEqualTo
import io.github.tomplum.aoc.input.TestInputReader
import io.github.tomplum.aoc.train.ticket.TicketReader
import io.github.tomplum.aoc.train.ticket.TicketRule
import org.junit.jupiter.api.Test

class TicketMetaAnalyserTest {
    @Test
    fun example() {
        val exampleInput = TestInputReader.read<String>("train/notes/example-2.txt").asSingleString()
        val document = TicketReader.read(exampleInput)
        val meta = TicketMetaAnalyser(document.rules).analyse(document.nearbyTickets)
        assertThat(meta).isEqualTo(getExpectedMeta())
    }

    private fun getExpectedMeta(): TicketMeta {
        val meta = TicketMeta()
        meta.addRule(0, TicketRule("row", 0..5, 8..19))
        meta.addRule(1, TicketRule("class", 0..1, 4..19))
        meta.addRule(2, TicketRule("seat", 0..13, 16..19))
        return meta
    }
}