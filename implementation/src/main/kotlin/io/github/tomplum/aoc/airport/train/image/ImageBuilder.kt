package io.github.tomplum.aoc.airport.train.image

import io.github.tomplum.aoc.airport.train.image.Edge.*
import io.github.tomplum.libs.math.point.Point2D
import kotlin.math.sqrt

data class ImageBuilder(val tiles: List<ImageTile>) {
    fun assemble(): Long {
        val width = sqrt(tiles.size.toDouble()).toInt()

        //val trimmedTiles = tiles.map { it.removeEdges() }

        val allTileOrientations = tiles.flatMap { it.getOrientations() }
        val allTileOrientationsKeyed = tiles.map { it.id to it.getOrientations() }
        val actualTopLeft = allTileOrientationsKeyed[1].second[1] //TOP: 564, LEFT: 587, RIGHT: 318, BOTTOM: 710
        val tops = allTileOrientations.map { it.getEdge(TOP) }
        val lefts = allTileOrientations.map { it.getEdge(LEFT) }
        val rights = allTileOrientations.map { it.getEdge(RIGHT) }
        val bottoms = allTileOrientations.map { it.getEdge(BOTTOM) }

        val corners = allTileOrientations.filter { tileState ->
            val top = tileState.getEdge(TOP)
            val left = tileState.getEdge(LEFT)
            tops.count { it == top } == 1 && lefts.count { it == left } == 1
        }

        val topLeftCornerCandidates = allTileOrientations.filter { tile ->
            val rightEdge = tile.getEdge(RIGHT)
            val bottomEdge = tile.getEdge(BOTTOM)
            val topEdge = tile.getEdge(TOP)
            val leftEdge = tile.getEdge(LEFT)
            val otherTiles = allTileOrientations.filter { it.id != tile.id }
            val hasSingleRightEdgeMatch = otherTiles.count { it.getEdge(LEFT) == rightEdge } == 1
            val hasSingleBottomEdgeMatch = otherTiles.count { it.getEdge(TOP) == bottomEdge } == 1
            val hasNoMatchingTopEdges = otherTiles.count { it.getEdge(TOP) == topEdge } == 0
            val hasNoMatchingLeftEdges = otherTiles.count { it.getEdge(LEFT) == leftEdge } == 0
            hasSingleBottomEdgeMatch && hasSingleRightEdgeMatch && hasNoMatchingTopEdges && hasNoMatchingLeftEdges
        }

        val imageTileMapping = ImageTileMapping()
        val chosenTopLeft = topLeftCornerCandidates.first()
        imageTileMapping.addSection(Point2D.origin(), chosenTopLeft)

        var lastTileAdded = chosenTopLeft
        var leftMostInCurrentRow = chosenTopLeft

        (0 until width).forEach { y ->

            if (y != 0) {
                val bottomEdge = leftMostInCurrentRow.getEdge(BOTTOM)
                val matches = allTileOrientations.filter { it.id != leftMostInCurrentRow.id }.filter { it.getEdge(TOP) == bottomEdge }
                if (matches.size != 1) throw IllegalStateException("There are ${matches.size} for a bottom edge match for ${leftMostInCurrentRow.id}")
                val matchedTile = matches.first()
                imageTileMapping.addSection(Point2D(0, y), matchedTile)
                leftMostInCurrentRow = matchedTile
                lastTileAdded = leftMostInCurrentRow
            }

            (1 until width).forEach { x ->
                val pos = Point2D(x, y)
                val lastTilesRight = lastTileAdded.getEdge(RIGHT)
                val matches = allTileOrientations.filter { it.id != lastTileAdded.id }.filter { it.getEdge(LEFT) == lastTilesRight }
                if (matches.size != 1) throw IllegalStateException("There are ${matches.size} for a right edge match for ${lastTileAdded.id}")

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

        return correctRotation!!.getHabitatWaterRoughness().toLong()
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