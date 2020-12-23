package io.github.tomplum.aoc.raft.cards

import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class PlayerTest {
    @Nested
    inner class ToString {
        @Test
        fun playerOne() {
            assertThat(Player.PLAYER_1.toString()).isEqualTo("1")
        }

        @Test
        fun playerTwo() {
            assertThat(Player.PLAYER_2.toString()).isEqualTo("2")
        }
    }
}