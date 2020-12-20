package io.github.tomplum.aoc.airport.train.image

import io.github.tomplum.aoc.extensions.product
import kotlin.math.sqrt

data class ImageArray(val tiles: List<ImageTile>) {
    fun assemble(): Long {
        val width = sqrt(tiles.size.toDouble()).toInt()
        val unusedTiles = tiles.map { it.id to it }.toMap().toMutableMap()

        //1951 is top left. yFlip gets to correct orientation. Top edge is 564, Left 587
       // val corner = tiles.find { it.id == 1951 }!!.getOrientations().map { it to it.getEdge(Edge.TOP) }


        val allTileOrientations = tiles.flatMap { it.getOrientations() }
        val allTopEdgePermutations = allTileOrientations.map { it.getEdge(Edge.TOP) }
        val allLeftEdgePermutations = allTileOrientations.map { it.getEdge(Edge.LEFT) }
        val topLeftCorner = allTileOrientations.filter { tileState ->
            val top = tileState.getEdge(Edge.TOP)
            val left = tileState.getEdge(Edge.LEFT)
            allTopEdgePermutations.count { it == top } == 1 && allLeftEdgePermutations.count { it == left } == 1
        }
        return topLeftCorner.distinctBy { it.id }.map { it.id.toLong() }.product()
    }

    private fun ImageTile.getOrientations(): List<ImageTile> {
        val flipped = listOf(xFlip(), yFlip(), xFlip().yFlip())
        val rotated = flipped.flatMap {
            listOf(
                it.rotateClockwise(90), //90
                it.rotateClockwise(90).rotateClockwise(90), //180
                it.rotateClockwise(90).rotateClockwise(90).rotateClockwise(90), //270
            )
        }

        return (flipped + rotated).distinct()
    }

}