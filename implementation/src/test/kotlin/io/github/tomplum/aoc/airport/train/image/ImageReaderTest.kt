package io.github.tomplum.aoc.airport.train.image

import assertk.assertThat
import assertk.assertions.isEqualTo
import io.github.tomplum.aoc.input.TestInputReader
import org.junit.jupiter.api.Test

class ImageReaderTest {
    @Test
    fun smallExample() {
        val input = TestInputReader.read<String>("train/images/small-example.txt")
        val imageArray = ImageReader.read(input.asSingleString())
        assertThat(imageArray).isEqualTo(getExpectedImageArray())
    }

    private fun getExpectedImageArray(): ImageArray {
        val tile1 = ImageTile(1, listOf("##.#", ".#.#", "#..#", ".##."))
        val tile2 = ImageTile(2, listOf("#.##", "#..#", "#.##", ".#.."))
        val tile3 = ImageTile(3, listOf(".##.", ".##.", "..##", "###."))
        val tile4 = ImageTile(4, listOf(".#..", ".###", "##..", "..#."))
        return ImageArray(listOf(tile1, tile2, tile3, tile4))
    }
}