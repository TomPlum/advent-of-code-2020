package io.github.tomplum.aoc.aircraft.energy

import assertk.assertThat
import assertk.assertions.isEqualTo
import assertk.assertions.isFalse
import assertk.assertions.isTrue
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class ConwayCubeTest {
    @Nested
    inner class IsActive {
        @Test
        fun active() {
            assertThat(ConwayCube('#').isActive()).isTrue()
        }

        @ParameterizedTest
        @ValueSource(chars = ['.', 'A', '4', '-'])
        fun inactive(state: Char) {
            assertThat(ConwayCube(state).isActive()).isFalse()
        }
    }

    @Nested
    inner class Companion {
        @Test
        fun active() {
            assertThat(ConwayCube.active()).isEqualTo(ConwayCube('#'))
        }

        @Test
        fun inactive() {
            assertThat(ConwayCube.inactive()).isEqualTo(ConwayCube('.'))
        }
    }
}