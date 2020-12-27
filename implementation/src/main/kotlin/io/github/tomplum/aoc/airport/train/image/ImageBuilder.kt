package io.github.tomplum.aoc.airport.train.image

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
            //Find the left-most tile on the next row.
            if (y != 0) {
                val matches = tileOrientations
                    .filter { tile -> tile.id != leftMostInCurrentRow.id }
                    .filter { tile -> tile.topEdge == leftMostInCurrentRow.bottomEdge }
                val matchedTile = matches.first()
                imageTileMapping.addSection(Point2D(0, y), matchedTile)
                leftMostInCurrentRow = matchedTile
                lastTileAdded = leftMostInCurrentRow
            }

            //Find and match all the tiles to the right of the left-most tile to form the row.
            (1 until width).forEach { x ->
                val lastTilesRight = lastTileAdded.rightEdge
                val matches = tileOrientations
                    .filter { tile -> tile.id != lastTileAdded.id }
                    .filter { tile -> tile.leftEdge == lastTilesRight }

                val matchedTile = matches.first()
                imageTileMapping.addSection(Point2D(x, y), matchedTile)
                lastTileAdded = matchedTile
            }
        }

        val trimmedImageMapping = imageTileMapping.trimSectionsForAssembly()

        val image = Image.assembleFromMapping(trimmedImageMapping)
        val imageOrientations = image.getOrientations()
        return imageOrientations.find { candidate -> candidate.containsSeaMonsters() }!!
    }

    private fun getCornerCandidates(orientations: List<ImageTile>): List<ImageTile> = orientations.filter { tile ->
        val otherTiles = orientations.filter { orientation -> orientation.id != tile.id }
        val hasNoMatchingTopEdges = otherTiles.count { other -> other.topEdge == tile.topEdge } == 0
        val hasNoMatchingLeftEdges = otherTiles.count { other -> other.leftEdge == tile.leftEdge } == 0
        hasNoMatchingTopEdges && hasNoMatchingLeftEdges
    }

}