package io.github.tomplum.aoc.airport.train.image

import io.github.tomplum.aoc.extensions.product
import kotlin.math.sqrt

data class ImageArray(val tiles: List<ImageTile>) {
    fun assemble(): Long {
        val width = sqrt(tiles.size.toDouble()).toInt()
        val unusedTiles = tiles.map { it.id to it }.toMap().toMutableMap()

        val allTileOrientations = tiles.flatMap { it.getOrientations() }
        val tops = allTileOrientations.map { it.getEdge(Edge.TOP) }
        val lefts = allTileOrientations.map { it.getEdge(Edge.LEFT) }
        val rights = allTileOrientations.map { it.getEdge(Edge.RIGHT) }
        val bottoms = allTileOrientations.map { it.getEdge(Edge.BOTTOM) }

        val corners = allTileOrientations.filter { tileState ->
            val top = tileState.getEdge(Edge.TOP)
            val left = tileState.getEdge(Edge.LEFT)
            tops.count { it == top } == 1 && lefts.count { it == left } == 1
        }

        var topLeftCorner: ImageTile
        var topRightCorner: ImageTile
        var bottomLeftCorner: ImageTile
        var bottomRightCorner: ImageTile

        corners.distinctBy { it.id }.forEach { corner ->
            val isTop = tops.count { it == corner.getEdge(Edge.TOP) } == 1
            val isLeft = lefts.count { it == corner.getEdge(Edge.LEFT) } == 1
            val isBottom = bottoms.count { it == corner.getEdge(Edge.BOTTOM) } == 1
            val isRight = rights.count { it == corner.getEdge(Edge.RIGHT) } == 1

            when {
                isTop && isLeft -> topLeftCorner = corner
                isTop && isRight -> topRightCorner = corner
                isBottom && isLeft -> bottomLeftCorner = corner
                isBottom && isRight -> bottomRightCorner = corner
            }
        }

        return corners.distinctBy { it.id }.map { it.id.toLong() }.product()
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