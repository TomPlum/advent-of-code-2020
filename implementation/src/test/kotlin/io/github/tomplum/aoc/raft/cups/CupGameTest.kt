package io.github.tomplum.aoc.raft.cups

import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.Test

class CupGameTest {
    @Test
    fun example() {
        assertThat(CupGame("389125467").simulate(100)).isEqualTo(67384529)
    }
}