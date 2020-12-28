package io.github.tomplum.aoc.airport.train.image

import assertk.assertThat
import assertk.assertions.isEqualTo
import assertk.assertions.isFalse
import assertk.assertions.isTrue
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class ImageTileDataTest {
    @Nested
    inner class IsWave {
        @Test
        fun isWave() {
            assertThat(ImageTileData('#').isWave()).isTrue()
        }

        @ParameterizedTest
        @ValueSource(chars = ['O', '.', '-', '1'])
        fun isNotWave(value: Char) {
            assertThat(ImageTileData(value).isWave()).isFalse()
        }
    }

    @Nested
    inner class IsSeaMonster {
        @Test
        fun isSeaMonster() {
            assertThat(ImageTileData('O').isSeaMonster()).isTrue()
        }

        @ParameterizedTest
        @ValueSource(chars = ['#', '.', '-', '1'])
        fun isNotWave(value: Char) {
            assertThat(ImageTileData(value).isSeaMonster()).isFalse()
        }
    }

    @Nested
    inner class ToBinary {
        @Test
        fun wave() {
            assertThat(ImageTileData('#').toBinary()).isEqualTo('1')
        }

        @Test
        fun sea() {
            assertThat(ImageTileData('.').toBinary()).isEqualTo('0')
        }
    }

    @Nested
    inner class ToString {
        @Test
        fun wave() {
            assertThat(ImageTileData('#').toString()).isEqualTo("#")
        }

        @Test
        fun sea() {
            assertThat(ImageTileData('.').toString()).isEqualTo(".")
        }
    }
}