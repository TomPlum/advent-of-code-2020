package io.github.tomplum.aoc.raft.cups

import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test

class TranslatedCupGameTest {
    @Test
    fun example() {
        assertThat(TranslatedCupGame("389125467").simulate(10_000_000)).isEqualTo(149245887792)
    }
}