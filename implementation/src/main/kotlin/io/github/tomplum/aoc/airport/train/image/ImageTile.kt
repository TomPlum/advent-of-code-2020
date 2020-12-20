package io.github.tomplum.aoc.airport.train.image

import io.github.tomplum.libs.math.Point2D
import io.github.tomplum.libs.math.map.AdventMap2D

data class ImageTile(val id: Int, val data: List<String>): AdventMap2D<ImageData>() {

    private val xMax: Int
    private val yMax: Int

    init {
        var x = 0
        var y = 0
        data.forEach { row ->
            row.forEach { chroming ->
                addTile(Point2D(x, y), ImageData(chroming))
                x++
            }
            x = 0
            y++
        }
        xMax = xMax()!!
        yMax = yMax()!!
    }

    fun xFlip(): ImageTile {
        val flipped = ImageTile(id, emptyList())
        snapshot().forEach { (pos, tile) ->
            val posFlipped = Point2D(xMax - pos.x, pos.y)
            flipped.addTile(posFlipped, tile)
        }
        return flipped;
    }

    fun yFlip(): ImageTile {
        val flipped = ImageTile(id, emptyList())
        snapshot().forEach { (pos, tile) ->
            val posFlipped = Point2D(pos.x, yMax - pos.y)
            flipped.addTile(posFlipped, tile)
        }
        return flipped;
    }

    fun rotateClockwise90(): ImageTile {
        val rotated = ImageTile(id, emptyList())
        snapshot().forEach { (pos, tile) ->
            val posRotated = Point2D(pos.x, pos.y)
            rotated.addTile(pos, tile)
        }
        return rotated
    }
}