package io.github.tomplum.aoc.airport.train.image

import io.github.tomplum.aoc.airport.train.image.Edge.*
import io.github.tomplum.libs.math.Direction
import io.github.tomplum.libs.math.Point2D
import io.github.tomplum.libs.math.map.AdventMap2D
import kotlin.math.abs

class ImageTile(val id: Int, initialData: List<String>) : AdventMap2D<ImageData>() {

    private var xMax = 0
    private var yMax = 0
    private var xMin = 0
    private var yMin = 0

    init {
        var x = 0
        var y = 0
        initialData.forEach { row ->
            row.forEach { chroming ->
                addTile(Point2D(x, y), ImageData(chroming))
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

    fun xFlip(): ImageTile = snapshot().entries.fold(ImageTile(id, emptyList())) { flipped, (pos, tile) ->
        val posFlipped = Point2D(xMax - pos.x, pos.y)
        flipped.apply { addTile(posFlipped, tile) }
    }.updateMaxOrdinates()


    fun yFlip(): ImageTile = snapshot().entries.fold(ImageTile(id, emptyList())) { flipped, (pos, tile) ->
        val posFlipped = Point2D(pos.x, yMax - pos.y)
        flipped.apply { addTile(posFlipped, tile) }
    }.updateMaxOrdinates()

    fun rotateClockwise(degrees: Int): ImageTile = snapshot()
        .entries.fold(ImageTile(id, emptyList())) { rotated, (pos, tile) ->
            val yInverted = Point2D(pos.x, -pos.y) //Convert regular cartesian -> single quadrant (y is down)
            val posRotated = yInverted.rotateAbout(Point2D.origin(), degrees) //Rotate about origin
            val posShifted = posRotated.shift(Direction.RIGHT, xMax) //Shift back into our quadrant
            val yCorrected = Point2D(posShifted.x, abs(posShifted.y)) //Flip y back to our quadrant
            rotated.apply { addTile(yCorrected, tile) }
        }.updateMaxOrdinates()

    fun getEdge(edge: Edge): Long = when(edge) {
        TOP -> (xMin..xMax).map { x -> Point2D(x, yMin) }
        RIGHT -> (yMin..yMax).map { y -> Point2D(xMax, y) }
        BOTTOM -> (xMin..xMax).map { x -> Point2D(x, yMax) }
        LEFT -> (yMin..yMax).map { y -> Point2D(xMin, y) }
    }.let { edgePoints ->
        val edgeTiles = filterPoints(edgePoints)
        val sorted = edgeTiles.toSortedMap(compareBy<Point2D> { it.x }.thenBy { it.y })
        sorted.values.map { it.toBinary() }.joinToString("").toLong(2)
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