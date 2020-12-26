package io.github.tomplum.aoc.raft.cups

import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.Test

class CupGameTest {
    @Test
    fun exampleTenMoves() {
        assertThat(CupGame("389125467").simulate(10)).isEqualTo("92658374")
    }

    @Test
    fun exampleHundredMoves() {
        assertThat(CupGame("389125467").simulate(100)).isEqualTo("67384529")
    }
}