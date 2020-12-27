package io.github.tomplum.aoc.airport.train.image

import io.github.tomplum.aoc.airport.train.image.Edge.*
import io.github.tomplum.libs.math.Direction
import io.github.tomplum.libs.math.map.AdventMap2D
import io.github.tomplum.libs.math.point.Point2D
import kotlin.math.abs

class ImageTile(val id: Int, initialData: List<String>) : AdventMap2D<ImageTileData>() {

    private var xMax = 0
    private var yMax = 0
    private var xMin = 0
    private var yMin = 0

    init {
        var x = 0
        var y = 0
        initialData.forEach { row ->
            row.forEach { chroming ->
                addTile(Point2D(x, y), ImageTileData(chroming))
                x++
            }
            x = 0
            y++
        }
        xMax = xMax() ?: 0
        yMax = yMax() ?: 0
        xMin = xMin() ?: 0
        yMin = yMin() ?: 0
    }

    fun getData(x: Int, y: Int): ImageTileData = getTile(Point2D(x, y))

    fun getOrientations(): List<ImageTile> {
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

    fun xFlip(): ImageTile = data.entries.fold(ImageTile(id, emptyList())) { flipped, (pos, tile) ->
        val posFlipped = Point2D(xMax - pos.x, pos.y)
        flipped.apply { addTile(posFlipped, tile) }
    }.updateMaxOrdinates()


    fun yFlip(): ImageTile = data.entries.fold(ImageTile(id, emptyList())) { flipped, (pos, tile) ->
        val posFlipped = Point2D(pos.x, yMax - pos.y)
        flipped.apply { addTile(posFlipped, tile) }
    }.updateMaxOrdinates()

    fun rotateClockwise(degrees: Int): ImageTile = data
        .entries.fold(ImageTile(id, emptyList())) { rotated, (pos, tile) ->
            val yInverted = Point2D(pos.x, -pos.y) //Convert regular cartesian -> single quadrant (y is down)
            val posRotated = yInverted.rotateAbout(Point2D.origin(), degrees) //Rotate about origin
            val posShifted = posRotated.shift(Direction.RIGHT, xMax) //Shift back into our quadrant
            val yCorrected = Point2D(posShifted.x, abs(posShifted.y)) //Flip y back to our quadrant
            rotated.apply { addTile(yCorrected, tile) }
        }.updateMaxOrdinates()

    fun getEdge(edge: Edge): Long = getEdgePoints(edge).let { edgePoints ->
        val edgeTiles = filterPoints(edgePoints.toSet())
        val sorted = edgeTiles.toSortedMap(compareBy<Point2D> { it.x }.thenBy { it.y })
        sorted.values.map { it.toBinary() }.joinToString("").toLong(2)
    }

    fun removeEdges(): ImageTile {
        Edge.values().flatMap { edge -> getEdgePoints(edge) }.forEach { pos -> removeTile(pos) }
        val pixels = data.toMap()
        reset()
        pixels.forEach { (pos, tile) -> addTile(pos.shift(Direction.LEFT).shift(Direction.DOWN), tile) }
        updateMaxOrdinates()
        return this
    }

    fun width(): Int = xMax

    private fun getEdgePoints(edge: Edge): List<Point2D> = when(edge) {
        TOP -> (xMin..xMax).map { x -> Point2D(x, yMin) }
        RIGHT -> (yMin..yMax).map { y -> Point2D(xMax, y) }
        BOTTOM -> (xMin..xMax).map { x -> Point2D(x, yMax) }
        LEFT -> (yMin..yMax).map { y -> Point2D(xMin, y) }
    }

    private fun updateMaxOrdinates(): ImageTile {
        xMax = xMax() ?: 0
        yMax = yMax() ?: 0
        xMin = xMin() ?: 0
        yMin = yMin() ?: 0
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