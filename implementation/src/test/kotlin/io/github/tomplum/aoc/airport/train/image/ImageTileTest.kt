package io.github.tomplum.aoc.airport.train.image

import assertk.assertThat
import assertk.assertions.isEqualTo
import assertk.assertions.isNotEqualTo
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class ImageTileTest {
    @Nested
    inner class FlipX {
        @Test
        fun exampleTile1() {
            val tile1 = ImageTile(1, listOf("##.#", ".#.#", "#..#", ".##."))
            val flipped = tile1.xFlip()
            assertThat(flipped).isEqualTo(ImageTile(1, listOf("#.##", "#.#.", "#..#", ".##.")))
        }

        @Test
        fun exampleTile2() {
            val tile2 = ImageTile(2, listOf("#.##", "#..#", "#.##", ".#.."))
            val flipped = tile2.xFlip()
            assertThat(flipped).isEqualTo(ImageTile(2, listOf("##.#", "#..#", "##.#", "..#.")))
        }

        @Test
        fun exampleTile3() {
            val tile3 = ImageTile(3, listOf(".##.", ".##.", "..##", "###."))
            val flipped = tile3.xFlip()
            assertThat(flipped).isEqualTo(ImageTile(3, listOf(".##.", ".##.", "##..", ".###")))
        }

        @Test
        fun exampleTile4() {
            val tile4 = ImageTile(4, listOf(".#..", ".###", "##..", "..#."))
            val flipped = tile4.xFlip()
            assertThat(flipped).isEqualTo(ImageTile(4, listOf("..#.", "###.", "..##", ".#..")))
        }
    }

    @Nested
    inner class FlipY {
        @Test
        fun exampleTile1() {
            val tile1 = ImageTile(1, listOf("##.#", ".#.#", "#..#", ".##."))
            val flipped = tile1.yFlip()
            assertThat(flipped).isEqualTo(ImageTile(1, listOf(".##.", "#..#", ".#.#", "##.#")))
        }

        @Test
        fun exampleTile2() {
            val tile2 = ImageTile(2, listOf("#.##", "#..#", "#.##", ".#.."))
            val flipped = tile2.yFlip()
            assertThat(flipped).isEqualTo(ImageTile(2, listOf(".#..", "#.##", "#..#", "#.##")))
        }

        @Test
        fun exampleTile3() {
            val tile3 = ImageTile(3, listOf(".##.", ".##.", "..##", "###."))
            val flipped = tile3.yFlip()
            assertThat(flipped).isEqualTo(ImageTile(3, listOf("###.", "..##", ".##.", ".##.")))
        }

        @Test
        fun exampleTile4() {
            val tile4 = ImageTile(4, listOf(".#..", ".###", "##..", "..#."))
            val flipped = tile4.yFlip()
            assertThat(flipped).isEqualTo(ImageTile(4, listOf("..#.", "##..", ".###", ".#..")))
        }
    }

    @Nested
    inner class RotateClockwise90 {
        @Test
        fun exampleTile1() {
            val tile1 = ImageTile(1, listOf("##.#", ".#.#", "#..#", ".##."))
            val flipped = tile1.rotateClockwise(90)
            assertThat(flipped).isEqualTo(ImageTile(1, listOf(".#.#", "#.##", "#...", ".###")))
        }

        @Test
        fun exampleTile2() {
            val tile2 = ImageTile(2, listOf("#.##", "#..#", "#.##", ".#.."))
            val flipped = tile2.rotateClockwise(90)
            assertThat(flipped).isEqualTo(ImageTile(2, listOf(".###", "#...", ".#.#", ".###")))
        }

        @Test
        fun exampleTile3() {
            val tile3 = ImageTile(3, listOf(".##.", ".##.", "..##", "###."))
            val flipped = tile3.rotateClockwise(90)
            assertThat(flipped).isEqualTo(ImageTile(3, listOf("#...", "#.##", "####", ".#..")))
        }

        @Test
        fun exampleTile4() {
            val tile4 = ImageTile(4, listOf(".#..", ".###", "##..", "..#."))
            val flipped = tile4.rotateClockwise(90)
            assertThat(flipped).isEqualTo(ImageTile(4, listOf(".#..", ".###", "#.#.", "..#.")))
        }
    }

    @Nested
    inner class GetEdge {
        @Test
        fun right() {
            val tile = ImageTile(1, listOf("##.#", ".#.#", "#..#", ".##."))
            val edge = tile.getEdge(Edge.RIGHT)
            assertThat(edge).isEqualTo(14)
        }

        @Test
        fun bottom() {
            val tile = ImageTile(1, listOf("##.#", ".#.#", "#..#", ".##."))
            val edge = tile.getEdge(Edge.BOTTOM)
            assertThat(edge).isEqualTo(6)
        }

        @Test
        fun left() {
            val tile = ImageTile(1, listOf("##.#", ".#.#", "#..#", ".##."))
            val edge = tile.getEdge(Edge.LEFT)
            assertThat(edge).isEqualTo(10)
        }

        @Test
        fun top() {
            val tile = ImageTile(1, listOf("##.#", ".#.#", "#..#", ".##."))
            val edge = tile.getEdge(Edge.TOP)
            assertThat(edge).isEqualTo(13)
        }

        @Test
        fun topFlippedX() {
            val tile = ImageTile(1, listOf("##.#", ".#.#", "#..#", ".##."))
            val edge = tile.xFlip().getEdge(Edge.TOP)
            assertThat(edge).isEqualTo(11)
        }

        @Test
        fun leftFlippedY() {
            val tile = ImageTile(1, listOf("##.#", ".#.#", "#..#", ".##."))
            val edge = tile.yFlip().getEdge(Edge.LEFT)
            assertThat(edge).isEqualTo(5)
        }
    }

    @Nested
    inner class Equality {
        @Test
        fun equal() {
            val tile1 = ImageTile(1, listOf("##.#", ".#.#", "#..#", ".##."))
            val tile2 = ImageTile(1, listOf("##.#", ".#.#", "#..#", ".##."))
            assertThat(tile1).isEqualTo(tile2)
        }

        @Test
        fun differentId() {
            val tile1 = ImageTile(1, listOf("##.#", ".#.#", "#..#", ".##."))
            val tile2 = ImageTile(2, listOf("##.#", ".#.#", "#..#", ".##."))
            assertThat(tile1).isNotEqualTo(tile2)
        }

        @Test
        fun differentData() {
            val tile1 = ImageTile(1, listOf("##.#", ".#.#", "#..#", ".##."))
            val tile2 = ImageTile(1, listOf(".#.#", ".#..", "#...", ".#.."))
            assertThat(tile1).isNotEqualTo(tile2)
        }

        @Test
        fun differentIdAndData() {
            val tile1 = ImageTile(1, listOf("##.#", ".#.#", "#..#", ".##."))
            val tile2 = ImageTile(2, listOf(".#.#", ".#..", "#...", ".#.."))
            assertThat(tile1).isNotEqualTo(tile2)
        }
    }

    @Nested
    inner class RemoveEdges {
        @Test
        fun exampleTile1() {
            val tile1 = ImageTile(1, listOf("##.#", ".#.#", "#..#", ".##."))
            val trimmed = tile1.removeEdges()
            assertThat(trimmed).isEqualTo(ImageTile(1, listOf("#.", "..")))
        }

        @Test
        fun exampleTile2() {
            val tile2 = ImageTile(2, listOf("#.##", "#..#", "#.##", ".#.."))
            val trimmed = tile2.removeEdges()
            assertThat(trimmed).isEqualTo(ImageTile(2, listOf("..", ".#")))
        }

        @Test
        fun exampleTile3() {
            val tile3 = ImageTile(3, listOf(".##.", ".##.", "..##", "###."))
            val trimmed = tile3.removeEdges()
            assertThat(trimmed).isEqualTo(ImageTile(3, listOf("##", ".#")))
        }

        @Test
        fun exampleTile4() {
            val tile4 = ImageTile(4, listOf(".#..", ".###", "##..", "..#."))
            val trimmed = tile4.removeEdges()
            assertThat(trimmed).isEqualTo(ImageTile(4, listOf("##", "#.")))
        }
    }

    @Nested
    inner class ToString {
        @Test
        fun fourByFour() {
            val tile = ImageTile(1, listOf("##.#", ".#.#", "#..#", ".##."))
            val toString = tile.toString()
            assertThat(toString).isEqualTo("# # . #\n. # . #\n# . . #\n. # # .\n")
        }
    }
}