package io.github.tomplum.aoc.island.lobby

import assertk.assertThat
import assertk.assertions.isEqualTo
import assertk.assertions.isFalse
import assertk.assertions.isTrue
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class FloorTileTest {
    @Nested
    inner class Companion {
        @Test
        fun white() {
            assertThat(FloorTile.white()).isEqualTo(FloorTile('W'))
        }

        @Test
        fun black() {
            assertThat(FloorTile.black()).isEqualTo(FloorTile('B'))
        }
    }

    @Nested
    inner class IsWhite {
        @Test
        fun isWhite() {
            assertThat(FloorTile('W').isWhite()).isTrue()
        }

        @ParameterizedTest
        @ValueSource(chars = ['B', '.', '#'])
        fun isNotWhite(value: Char) {
            assertThat(FloorTile(value).isWhite()).isFalse()
        }
    }

    @Nested
    inner class IsBlack {
        @Test
        fun isBlack() {
            assertThat(FloorTile('B').isBlack()).isTrue()
        }

        @ParameterizedTest
        @ValueSource(chars = ['W', '.', '#'])
        fun isNotBlack(value: Char) {
            assertThat(FloorTile(value).isBlack()).isFalse()
        }
    }

    @Nested
    inner class Flip {
        @Test
        fun black() {
            assertThat(FloorTile.black().flip()).isEqualTo(FloorTile.white())
        }

        @Test
        fun white() {
            assertThat(FloorTile.white().flip()).isEqualTo(FloorTile.black())
        }

        @ParameterizedTest
        @ValueSource(chars = ['R', '.', '#'])
        fun invalidColour(colour: Char) {
            val e = assertThrows<IllegalArgumentException> { FloorTile(colour).flip() }
            assertThat(e.message).isEqualTo("Invalid Floor Tile: $colour")
        }
    }

    @Nested
    inner class ToString {
        @Test
        fun black() {
            assertThat(FloorTile('B').toString()).isEqualTo("B")
        }

        @Test
        fun white() {
            assertThat(FloorTile('W').toString()).isEqualTo("W")
        }
    }
}