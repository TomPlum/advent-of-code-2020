package io.github.tomplum.aoc.airport.train.image

import io.github.tomplum.libs.math.Direction
import io.github.tomplum.libs.math.Point2D
import io.github.tomplum.libs.math.map.AdventMap2D
import kotlin.math.abs

class ImageTile(val id: Int, initialData: List<String>): AdventMap2D<ImageData>() {

    private val xMax: Int
    private val yMax: Int

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
    }

    fun xFlip(): ImageTile {
        val flipped = ImageTile(id, emptyList())
        snapshot().forEach { (pos, tile) ->
            val posFlipped = Point2D(xMax - pos.x, pos.y)
            flipped.addTile(posFlipped, tile)
        }
        return flipped
    }

    fun yFlip(): ImageTile {
        val flipped = ImageTile(id, emptyList())
        snapshot().forEach { (pos, tile) ->
            val posFlipped = Point2D(pos.x, yMax - pos.y)
            flipped.addTile(posFlipped, tile)
        }
        return flipped
    }

    fun rotateClockwise(degrees: Int): ImageTile {
        val rotated = ImageTile(id, emptyList())
        snapshot().forEach { (pos, tile) ->
            val posRotated = Point2D(pos.x, -pos.y).rotateAbout(Point2D.origin(), degrees)
            val posShifted = posRotated.shift(Direction.RIGHT, xMax)
            val yCorrected = Point2D(posShifted.x, abs(posShifted.y))
            rotated.addTile(yCorrected, tile)
        }
        return rotated
    }

    override fun equals(other: Any?): Boolean {
        if (other !is ImageTile) return false
        return super.equals(other) && id == other.id
    }

    override fun hashCode(): Int {
        return id.hashCode() + super.hashCode()
    }
}