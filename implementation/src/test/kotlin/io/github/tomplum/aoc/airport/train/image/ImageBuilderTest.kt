package io.github.tomplum.aoc.airport.train.image

import assertk.assertThat
import assertk.assertions.isEqualTo
import io.github.tomplum.aoc.input.TestInputReader
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class ImageBuilderTest {
    @Nested
    inner class CornerIDProduct {
        @Test
        fun example() {
            val input = TestInputReader.read<String>("train/images/example.txt")
            val imageArray = ImageReader.read(input.asSingleString())
            assertThat(imageArray.getCornerTileIDProduct()).isEqualTo(20899048083289)
        }
    }

    @Nested
    inner class Assemble {
        @Test
        fun examplePartOne() {
            val input = TestInputReader.read<String>("train/images/example.txt")
            val imageArray = ImageReader.read(input.asSingleString())
            assertThat(imageArray.assemble()).isEqualTo(20899048083289)
        }
    }

}