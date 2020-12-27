package io.github.tomplum.aoc.airport.train.image

import assertk.assertThat
import assertk.assertions.containsOnly
import assertk.assertions.isEqualTo
import io.github.tomplum.aoc.input.TestInputReader
import org.junit.jupiter.api.Test

class ImageTileReaderTest {
    @Test
    fun smallExample() {
        val input = TestInputReader.read<String>("train/images/small-example.txt").asSingleString()
        val imageArray = ImageTileReader.read(input)
        assertThat(imageArray).isEqualTo(getExpectedImageTiles())
    }

    private fun getExpectedImageTiles(): List<ImageTile> {
        val tile1 = ImageTile.fromData(1, listOf("##.#", ".#.#", "#..#", ".##."))
        val tile2 = ImageTile.fromData(2, listOf("#.##", "#..#", "#.##", ".#.."))
        val tile3 = ImageTile.fromData(3, listOf(".##.", ".##.", "..##", "###."))
        val tile4 = ImageTile.fromData(4, listOf(".#..", ".###", "##..", "..#."))
        return listOf(tile1, tile2, tile3, tile4)
    }
}