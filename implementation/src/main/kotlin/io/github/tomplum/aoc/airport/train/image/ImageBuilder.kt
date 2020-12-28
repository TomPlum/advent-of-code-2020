package io.github.tomplum.aoc.airport.train.image

import io.github.tomplum.aoc.extensions.product
import io.github.tomplum.libs.math.point.Point2D
import kotlin.math.sqrt

/**
 * Builds an [Image] from a list of [ImageTile].
 * @param tiles A list of image tiles in their starting orientations.
 */
class ImageBuilder(private val tiles: List<ImageTile>) {

    private val tileOrientations = tiles.flatMap { tile -> tile.getOrientations() }

    /**
     * Finds the IDs of the four corner [ImageTile] of the [Image].
     * @return The product of all four IDs.
     */
    fun getCornerTileIDProduct(): Long = getCornerCandidates(tileOrientations)
        .map { tile -> tile.id.toLong() }
        .distinct()
        .product()

    /**
     * Assembles the [tiles] into a complete [Image]. The process is as follows;
     *  1. Chooses a random [ImageTile] from the list of [tileOrientations].
     *  2. Locates and arranges the tiles by matching edges and stores them in an [ImageTileMapping].
     *  3. Trims the edges of each of the tiles in the mapping and creates a complete [Image].
     *  4. Takes the built [Image] and produces a list of all eight possible orientations.
     *  5. Finds the correct orientation, locates sea monsters and calculates the water roughness.
     * @return The complete image in its correct orientation.
     */
    fun assemble(): Image {
        val mapping: ImageTileMapping = mapTilesTogether()
        val image = Image.assembleFromMapping(mapping)
        val imageOrientations = image.getOrientations()
        return imageOrientations.find { candidate -> candidate.containsSeaMonsters() }!!
    }

    /**
     * Maps together each of the unique tiles by matching their edges and trims them for assembly.
     * @return A mapping of all the tiles in one orientation (which may not be correct once assembled).
     */
    private fun mapTilesTogether(): ImageTileMapping {
        val imageTileMapping = ImageTileMapping()

        //Get all tiles that are candidates for corners and choose the first.
        //It doesn't matter which one, we'll build from it and we can rotate/flip to find the correct orientation.
        val cornerCandidates = getCornerCandidates(tileOrientations)
        val chosenCorner = cornerCandidates.first()
        imageTileMapping.addSection(Point2D.origin(), chosenCorner)

        var lastTileAdded = chosenCorner
        var leftMostInCurrentRow = chosenCorner

        val width = sqrt(tiles.size.toDouble()).toInt()
        (0 until width).forEach { y ->
            //Find the left-most tile on the next row (except for the first row as we add the first corner above)
            if (y != 0) {
                val matchedTile = tileOrientations
                    .filter { tile -> tile.id != leftMostInCurrentRow.id }
                    .find { tile -> tile.topEdge == leftMostInCurrentRow.bottomEdge }!!

                imageTileMapping.addSection(Point2D(0, y), matchedTile)
                leftMostInCurrentRow = matchedTile
                lastTileAdded = leftMostInCurrentRow
            }

            //Find and match all the tiles to the right of the left-most tile to form the row.
            (1 until width).forEach { x ->
                val lastTilesRight = lastTileAdded.rightEdge
                val matchedTile = tileOrientations
                    .filter { tile -> tile.id != lastTileAdded.id }
                    .find { tile -> tile.leftEdge == lastTilesRight }!!

                imageTileMapping.addSection(Point2D(x, y), matchedTile)
                lastTileAdded = matchedTile
            }
        }

        return imageTileMapping.trimSectionsForAssembly()
    }

    /**
     * Filters all possible [tileOrientations] and finds tiles that could be corners.
     * @return A list of corner tile candidates.
     */
    private fun getCornerCandidates(orientations: List<ImageTile>): List<ImageTile> = orientations.filter { tile ->
        val otherTiles = orientations.filter { orientation -> orientation.id != tile.id }
        val hasNoMatchingTopEdges = otherTiles.count { other -> other.topEdge == tile.topEdge } == 0
        val hasNoMatchingLeftEdges = otherTiles.count { other -> other.leftEdge == tile.leftEdge } == 0
        hasNoMatchingTopEdges && hasNoMatchingLeftEdges
    }

}