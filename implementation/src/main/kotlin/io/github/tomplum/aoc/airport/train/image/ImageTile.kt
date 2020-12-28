package io.github.tomplum.aoc.airport.train.image

import io.github.tomplum.aoc.airport.train.image.Edge.*
import io.github.tomplum.libs.math.Direction
import io.github.tomplum.libs.math.map.AdventMap2D
import io.github.tomplum.libs.math.point.Point2D
import kotlin.math.abs

class ImageTile(val id: Int, _width: Int): AdventMap2D<ImageTileData>() {

    var width: Int = _width; private set
    var topEdge: Int = 0; private set
    var leftEdge: Int = 0; private set
    var rightEdge: Int = 0; private set
    var bottomEdge: Int = 0; private set

    companion object {
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

    fun getData(position: Point2D): ImageTileData = getTile(position)

    fun getOrientations(): List<ImageTile> {
        val xFlipped = xFlip()
        val flipped = listOf(xFlipped, yFlip(), xFlipped.yFlip())
        val rotated = flipped.flatMap { tile ->
            val ninety = tile.rotateClockwise(90)
            val oneHundredEighty = ninety.rotateClockwise(90)
            val twoHundredSeventy = oneHundredEighty.rotateClockwise(90)
            listOf(ninety, oneHundredEighty, twoHundredSeventy)
        }

        return (flipped + rotated).distinct()
    }

    fun xFlip(): ImageTile = data.entries.fold(ImageTile(id, width)) { flipped, (pos, tile) ->
        val posFlipped = Point2D(width - pos.x, pos.y)
        flipped.apply { addTile(posFlipped, tile) }
    }.updateEdges()


    fun yFlip(): ImageTile = data.entries.fold(ImageTile(id, width)) { flipped, (pos, tile) ->
        val posFlipped = Point2D(pos.x, width - pos.y)
        flipped.apply { addTile(posFlipped, tile) }
    }.updateEdges()

    fun rotateClockwise(degrees: Int): ImageTile = data
        .entries.fold(ImageTile(id, width)) { rotated, (pos, tile) ->
            val yInverted = Point2D(pos.x, -pos.y) //Convert regular cartesian -> single quadrant (y is down)
            val posRotated = yInverted.rotateAbout(Point2D.origin(), degrees) //Rotate about origin
            val posShifted = posRotated.shift(Direction.RIGHT, width) //Shift back into our quadrant
            val yCorrected = Point2D(posShifted.x, abs(posShifted.y)) //Flip y back to our quadrant
            rotated.apply { addTile(yCorrected, tile) }
        }.updateEdges()

    private fun getEdge(edge: Edge): Int = getEdgePoints(edge).let { edgePoints ->
        val edgeTiles = filterPoints(edgePoints.toSet())
        val sorted = edgeTiles.toSortedMap(compareBy<Point2D> { pos -> pos.x }.thenBy { pos -> pos.y })
        sorted.values.map { tile -> tile.toBinary() }.joinToString("").toInt(2)
    }

    fun removeEdges(): ImageTile {
        Edge.values().flatMap { edge -> getEdgePoints(edge) }.forEach { pos -> removeTile(pos) }
        val pixels = data.toMap()
        reset()
        pixels.forEach { (pos, tile) -> addTile(pos.shift(Direction.LEFT).shift(Direction.DOWN), tile) }
        width -= 2
        updateEdges()
        return this
    }

    private fun getEdgePoints(edge: Edge): List<Point2D> = when(edge) {
        TOP -> (0..width).map { x -> Point2D(x, 0) }
        RIGHT -> (0..width).map { y -> Point2D(width, y) }
        BOTTOM -> (0..width).map { x -> Point2D(x, width) }
        LEFT -> (0..width).map { y -> Point2D(0, y) }
    }

    private fun updateEdges(): ImageTile {
        topEdge = getEdge(TOP)
        leftEdge = getEdge(LEFT)
        rightEdge = getEdge(RIGHT)
        bottomEdge = getEdge(BOTTOM)

        return this
    }

    override fun equals(other: Any?): Boolean {
        if (other !is ImageTile) return false
        return super.equals(other) && id == other.id
    }

    override fun hashCode(): Int {
        return id.hashCode() + super.hashCode()
    }
}