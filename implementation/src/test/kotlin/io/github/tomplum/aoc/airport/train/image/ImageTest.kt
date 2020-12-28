package io.github.tomplum.aoc.airport.train.image

import assertk.assertThat
import assertk.assertions.isEqualTo
import assertk.assertions.isFalse
import assertk.assertions.isTrue
import io.github.tomplum.aoc.input.TestInputReader
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class ImageTest {
    @Nested
    inner class GetWaterRoughness {
        @Test
        fun example() {
            val input = TestInputReader.read<String>("train/images/example.txt")
            val tiles = ImageTileReader.read(input.asSingleString())
            val image = ImageBuilder(tiles).assemble()
            assertThat(image.getWaterRoughness()).isEqualTo(273)
        }

        @Test
        fun blankImage() {
            assertThat(Image(0).getWaterRoughness()).isEqualTo(0)
        }
    }

    @Nested
    inner class ContainsSeaMonsters {
        @Test
        fun example() {
            val input = TestInputReader.read<String>("train/images/example.txt")
            val tiles = ImageTileReader.read(input.asSingleString())
            val image = ImageBuilder(tiles).assemble()
            assertThat(image.containsSeaMonsters()).isTrue()
        }

        @Test
        fun blankImage() {
            assertThat(Image(0).containsSeaMonsters()).isFalse()
        }
    }
}