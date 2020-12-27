package io.github.tomplum.aoc.airport.train.image

import io.github.tomplum.aoc.airport.train.image.Edge.*
import io.github.tomplum.aoc.extensions.product
import io.github.tomplum.libs.math.point.Point2D
import kotlin.math.sqrt

class ImageBuilder(private val tiles: List<ImageTile>) {

    private val tileOrientations = tiles.flatMap { tile -> tile.getOrientations() }

    fun getCornerTileIDProduct(): Long = getCornerCandidates(tileOrientations)
        .map { tile -> tile.id.toLong() }
        .distinct()
        .product()

    fun assemble(): Image {
        val cornerCandidates = getCornerCandidates(tileOrientations)

        val imageTileMapping = ImageTileMapping()
        val chosenCorner = cornerCandidates.first()
        imageTileMapping.addSection(Point2D.origin(), chosenCorner)

        var lastTileAdded = chosenCorner
        var leftMostInCurrentRow = chosenCorner

        val width = sqrt(tiles.size.toDouble()).toInt()
        (0 until width).forEach { y ->
            if (y != 0) {
                val bottomEdge = leftMostInCurrentRow.getEdge(BOTTOM)
                val matches = tileOrientations.filter { it.id != leftMostInCurrentRow.id }.filter { it.getEdge(TOP) == bottomEdge }
                val matchedTile = matches.first()
                imageTileMapping.addSection(Point2D(0, y), matchedTile)
                leftMostInCurrentRow = matchedTile
                lastTileAdded = leftMostInCurrentRow
            }

            (1 until width).forEach { x ->
                val pos = Point2D(x, y)
                val lastTilesRight = lastTileAdded.getEdge(RIGHT)
                val matches = tileOrientations.filter { it.id != lastTileAdded.id }.filter { it.getEdge(LEFT) == lastTilesRight }

                val matchedTile = matches.first()
                imageTileMapping.addSection(pos, matchedTile)
                lastTileAdded = matchedTile
            }
        }

        val trimmedImageMapping = imageTileMapping.trimSectionsForAssembly()
        val image = Image.assembleFromMapping(trimmedImageMapping)
        val imageOrientations = image.getOrientations()
        imageOrientations.forEach { it.locateSeaMonsters() }
        val correctRotation = imageOrientations.find { it.containsSeaMonsters() }

        return correctRotation!!
    }

    private fun getCornerCandidates(orientations: List<ImageTile>): List<ImageTile> = orientations.filter { tile ->
        val rightEdge = tile.getEdge(RIGHT)
        val bottomEdge = tile.getEdge(BOTTOM)
        val topEdge = tile.getEdge(TOP)
        val leftEdge = tile.getEdge(LEFT)
        val otherTiles = orientations.filter { it.id != tile.id }
        val hasSingleRightEdgeMatch = otherTiles.count { it.getEdge(LEFT) == rightEdge } == 1
        val hasSingleBottomEdgeMatch = otherTiles.count { it.getEdge(TOP) == bottomEdge } == 1
        val hasNoMatchingTopEdges = otherTiles.count { it.getEdge(TOP) == topEdge } == 0
        val hasNoMatchingLeftEdges = otherTiles.count { it.getEdge(LEFT) == leftEdge } == 0
        hasSingleBottomEdgeMatch && hasSingleRightEdgeMatch && hasNoMatchingTopEdges && hasNoMatchingLeftEdges
    }

}