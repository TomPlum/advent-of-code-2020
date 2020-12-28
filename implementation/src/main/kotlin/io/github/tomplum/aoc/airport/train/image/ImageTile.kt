package io.github.tomplum.aoc.airport.train.image

import io.github.tomplum.aoc.airport.train.image.ImageTile.Edge.*
import io.github.tomplum.libs.math.Direction
import io.github.tomplum.libs.math.map.AdventMap2D
import io.github.tomplum.libs.math.point.Point2D
import kotlin.math.abs

/**
 * A single tile of pixels in a larger [Image].
 * It may or may-not be in the correct orientation.
 * @param id The unique ID of the image tile. Persists across orientations of the same tile.
 * @param _width The width of the tile. Equal to the maximum ordinate.
 */
class ImageTile(val id: Int, _width: Int) : AdventMap2D<ImageTileData>() {

    var width: Int = _width; private set
    var topEdge: Int = 0; private set
    var leftEdge: Int = 0; private set
    var rightEdge: Int = 0; private set
    var bottomEdge: Int = 0; private set

    companion object {
        /**
         * Creates a new [ImageTile] from a list of monochromatic data rows.
         * The width is set to the number of rows - 1 as this will be the maximum ordinate.
         * @param id The unique ID of the image tile.
         * @param data A list of the rows of pixels. Must produce a square image tile.
         * @return The image tile with calculated edges.
         */
        fun fromData(id: Int, data: List<String>): ImageTile {
            val tile = ImageTile(id, data.size - 1)

            var x = 0
            var y = 0
            data.forEach { row ->
                row.forEach { chroming ->
                    tile.addTile(Point2D(x, y), ImageTileData(chroming))
                    x++
                }
                x = 0
                y++
            }

            return tile.updateEdges()
        }
    }

    /**
     * Retrieves a single [ImageTileData] from the [ImageTile].
     * @param position The position of the image pixel.
     * @return The datum from the image.
     */
    fun getData(position: Point2D): ImageTileData = getTile(position)

    /**
     * Retrieves the 8 possible orientations of the [ImageTile].
     * @return A list of the 8 copies in their new orientations.
     */
    fun getOrientations(): List<ImageTile> {
        val xFlipped = xFlip()
        val flipped = listOf(xFlipped, yFlip(), xFlipped.yFlip())
        val rotated = flipped.flatMap { tile ->
            val ninety = tile.rotateClockwise90()
            val oneHundredEighty = ninety.rotateClockwise90()
            val twoHundredSeventy = oneHundredEighty.rotateClockwise90()
            listOf(ninety, oneHundredEighty, twoHundredSeventy)
        }

        return (flipped + rotated).distinct()
    }

    /**
     * Flips the [ImageTile] about the X-Axis.
     * @return A copy of the image in its new orientation.
     */
    fun xFlip(): ImageTile = data.entries.fold(ImageTile(id, width)) { flipped, (pos, tile) ->
        val posFlipped = Point2D(width - pos.x, pos.y)
        flipped.apply { addTile(posFlipped, tile) }
    }.updateEdges()

    /**
     * Flips the [ImageTile] about the Y-Axis.
     * @return A copy of the image in its new orientation.
     */
    fun yFlip(): ImageTile = data.entries.fold(ImageTile(id, width)) { flipped, (pos, tile) ->
        val posFlipped = Point2D(pos.x, width - pos.y)
        flipped.apply { addTile(posFlipped, tile) }
    }.updateEdges()

    /**
     * Rotates the [ImageTile] 90 degrees about the logical centre of itself.
     * This is achieved by rotating the points about the origin and shifting them back to their original quadrant.
     * @return A copy of the image in its new orientation.
     */
    fun rotateClockwise90(): ImageTile = data.entries.fold(ImageTile(id, width)) { rotated, (pos, tile) ->
        val yInverted = Point2D(pos.x, -pos.y) //Convert regular cartesian -> single quadrant (y is down)
        val posRotated = yInverted.rotateAbout(Point2D.origin(), 90) //Rotate about origin
        val posShifted = posRotated.shift(Direction.RIGHT, width) //Shift back into our quadrant
        val yCorrected = Point2D(posShifted.x, abs(posShifted.y)) //Flip y back to our quadrant
        rotated.apply { addTile(yCorrected, tile) }
    }.updateEdges()

    /**
     * Retrieves the points from the given [Edge] of the [ImageTile].
     * @param edge The edge to retrieve.
     * @return The binary representation of the edge. See [ImageTileData.toBinary].
     */
    private fun getEdge(edge: Edge): Int = getEdgePoints(edge).let { edgePoints ->
        val edgeTiles = filterPoints(edgePoints.toSet())
        val sorted = edgeTiles.toSortedMap(compareBy<Point2D> { pos -> pos.x }.thenBy { pos -> pos.y })
        sorted.values.map { tile -> tile.toBinary() }.joinToString("").toInt(2)
    }

    /**
     * Removes all the edges from the tile.
     * Pixels are shifted back into place against the axes.
     * This reduces the overall width of the tile by 2.
     * @return the image with its edges removed.
     */
    fun removeEdges(): ImageTile {
        Edge.values().flatMap { edge -> getEdgePoints(edge) }.forEach { pos -> removeTile(pos) }
        val pixels = data.toMap()
        reset()
        pixels.forEach { (pos, tile) -> addTile(pos.shift(Direction.LEFT).shift(Direction.DOWN), tile) }
        width -= 2
        updateEdges()
        return this
    }

    /**
     * Retrieves all the points along a given [edge].
     * @param edge The edge to retrieve points from.
     * @return A list of the points from the edge. They are not removed.
     */
    private fun getEdgePoints(edge: Edge): List<Point2D> = when (edge) {
        TOP -> (0..width).map { x -> Point2D(x, 0) }
        RIGHT -> (0..width).map { y -> Point2D(width, y) }
        BOTTOM -> (0..width).map { x -> Point2D(x, width) }
        LEFT -> (0..width).map { y -> Point2D(0, y) }
    }

    /**
     * Updates the edges for the tile in its current orientation.
     * @return The tile with the updated edge properties.
     */
    private fun updateEdges(): ImageTile {
        topEdge = getEdge(TOP)
        leftEdge = getEdge(LEFT)
        rightEdge = getEdge(RIGHT)
        bottomEdge = getEdge(BOTTOM)

        return this
    }

    /**
     * An edge of the image. Can overlap with another on the same corner.
     */
    private enum class Edge {
        TOP, RIGHT, BOTTOM, LEFT
    }

    override fun equals(other: Any?): Boolean {
        if (other !is ImageTile) return false
        return super.equals(other) && id == other.id
    }

    override fun hashCode(): Int {
        return id.hashCode() + super.hashCode()
    }
}