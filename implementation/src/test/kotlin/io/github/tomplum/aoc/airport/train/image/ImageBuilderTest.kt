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
            val tiles = ImageTileReader.read(input.asSingleString())
            assertThat(ImageBuilder(tiles).getCornerTileIDProduct()).isEqualTo(20899048083289)
        }
    }

    @Nested
    inner class Assemble {
        @Test
        fun example() {
            val input = TestInputReader.read<String>("train/images/example.txt")
            val tiles = ImageTileReader.read(input.asSingleString())
            assertThat(ImageBuilder(tiles).assemble().toString()).isEqualTo(
                ". # # # # . . . # # # # # . . # . . . # # # . .\n" +
                "# # # # # . . # . . # . # . # # # # . . # . # .\n" +
                ". # . # . . . # . # # # . . . # . # # . O # . .\n" +
                "# . O . # # . O O # . # . O O . # # . O O O # #\n" +
                ". . # O . # O # . O # # O . . O . # O # # . # #\n" +
                ". . . # . # . . # # . # # . . . # . . # . . # #\n" +
                "# . # # . # . . # . # . . # . . # # . # . # . .\n" +
                ". # # # . # # . . . . . # . . . # # # . # . . .\n" +
                "# . # # # # . # . # . . . . # # . # . . # . # .\n" +
                "# # . . . # . . # . . . . # . . # . . . # # # #\n" +
                ". . # . # # . . . # # # . . # . # # # # # . . #\n" +
                ". . . . # . # # . # . # # # # # . . . . # . . .\n" +
                ". . # # . # # . # # # . . . . . # . # # . . # .\n" +
                "# . . . # . . . # # # . . # # # # . . . . # # .\n" +
                ". # . # # . . . # . # # . # . # . # # # . . . #\n" +
                "# . # # # . # . . # # # # . . . # # . . # . . .\n" +
                "# . # # # . . . # . # # . . . # . # # O # # # .\n" +
                ". O # # . # O O . # # # O O # # . . O O O # # .\n" +
                ". . O # . O . . O . . O . # O # # O # # . # # #\n" +
                "# . # . . # # . # # # # # # # # . . # . . # # .\n" +
                "# . # # # # # . . # . # . . . # # . . # . . . .\n" +
                "# . . . . # # . . # . # # # # # # # # # . . # #\n" +
                "# . . . # . . . . . # . . # # . . . # # # . # #\n" +
                "# . . # # # . . . . # # . # . . . # # . # # . #\n"
            )
        }
    }

}