package io.github.tomplum.aoc.forest.toboggan

import assertk.assertThat
import assertk.assertions.isFalse
import assertk.assertions.isTrue
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class ForestTileTest {
    @Nested
    inner class IsTree {
        @Test
        fun positive() {
            assertThat(ForestTile('#').isTree()).isTrue()
        }

        @ParameterizedTest
        @ValueSource(chars = ['.', 'a', '1', '-'])
        fun negative(value: Char) {
            assertThat(ForestTile(value).isTree()).isFalse()
        }
    }

    @Nested
    inner class IsEmpty {
        @Test
        fun positive() {
            assertThat(ForestTile('.').isEmpty()).isTrue()
        }

        @ParameterizedTest
        @ValueSource(chars = ['#', 'a', '1', '-'])
        fun negative(value: Char) {
            assertThat(ForestTile(value).isEmpty()).isFalse()
        }
    }
}