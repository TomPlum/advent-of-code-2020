package io.github.tomplum.aoc.airport.train.image

import io.github.tomplum.libs.math.Direction.*
import io.github.tomplum.libs.math.map.AdventMap2D
import io.github.tomplum.libs.math.point.Point2D
import kotlin.math.abs

class Image(private var width: Int = 0) : AdventMap2D<ImageTileData>() {

    companion object {
        fun assembleFromMapping(mapping: ImageTileMapping): Image {
            val image = Image()

            var y = 0
            mapping.getRows().forEach { row ->
                val sectionWidth = mapping.getSectionWidth()
                var x = 0
                (0..sectionWidth).forEach { sectionY ->
                    row.forEach { section ->
                        (0..sectionWidth).forEach { sectionX ->
                            val tileData = section.getData(Point2D(sectionX, sectionY))
                            image.addTile(Point2D(x, y), tileData)
                            x++
                        }
                    }
                    x = 0
                    y++
                }
            }

            image.width = y
            return image.locateSeaMonsters()
        }
    }

    fun getOrientations(): List<Image> {
        val xFlipped = xFlip()
        val flipped = listOf(xFlipped, yFlip(), xFlipped.yFlip())
        val rotated = flipped.flatMap { image ->
            val ninety = image.rotateClockwise(90)
            val oneHundredEighty = ninety.rotateClockwise(90)
            val twoHundredSeventy = oneHundredEighty.rotateClockwise(90)
            listOf(ninety, oneHundredEighty, twoHundredSeventy)
        }

        return (flipped + rotated).distinct().map { image -> image.locateSeaMonsters() }
    }

    fun getWaterRoughness(): Int = filterTiles { tile -> tile.isWave() }.count()

    fun containsSeaMonsters(): Boolean = data.values.any { tile -> tile.isSeaMonster() }

    private fun locateSeaMonsters(): Image {
        (1 until width).forEach { y ->
            (0..width - 19).forEach { x ->
                val position = Point2D(x, y)
                val positions = position.getSeaMonsterPositionCandidates()
                val isMatch = positions.map { pos -> data[pos] }.all { tile -> tile?.isWave() ?: false }
                if (isMatch) {
                    positions.forEach { pos -> addTile(pos, ImageTileData('O')) }
                }
            }
        }
        return this
    }

    private fun xFlip(): Image = data.entries.fold(Image(width)) { flipped, (pos, tile) ->
        val posFlipped = Point2D(width - pos.x, pos.y)
        flipped.apply { addTile(posFlipped, tile) }
    }


    private fun yFlip(): Image = data.entries.fold(Image(width)) { flipped, (pos, tile) ->
        val posFlipped = Point2D(pos.x, width - pos.y)
        flipped.apply { addTile(posFlipped, tile) }
    }

    private fun rotateClockwise(degrees: Int): Image = data.entries.fold(Image(width)) { rotated, (pos, tile) ->
        val yInverted = Point2D(pos.x, -pos.y) //Convert regular cartesian -> single quadrant (y is down)
        val posRotated = yInverted.rotateAbout(Point2D.origin(), degrees) //Rotate about origin
        val posShifted = posRotated.shift(RIGHT, width) //Shift back into our quadrant
        val yCorrected = Point2D(posShifted.x, abs(posShifted.y)) //Flip y back to our quadrant
        rotated.apply { addTile(yCorrected, tile) }
    }

    private fun Point2D.getSeaMonsterPositionCandidates(): List<Point2D> {
        val first = this
        val second = first.shift(UP, 1).shift(RIGHT, 1)
        val third = second.shift(RIGHT, 3)
        val fourth = third.shift(DOWN, 1).shift(RIGHT, 1)
        val fifth = fourth.shift(RIGHT, 1)
        val sixth = fifth.shift(RIGHT, 1).shift(UP, 1)
        val seventh = sixth.shift(RIGHT, 3)
        val eighth = seventh.shift(DOWN, 1).shift(RIGHT, 1)
        val ninth = eighth.shift(RIGHT, 1)
        val tenth = ninth.shift(RIGHT, 1).shift(UP, 1)
        val eleventh = tenth.shift(RIGHT, 3)
        val twelfth = eleventh.shift(DOWN, 1).shift(RIGHT, 1)
        val thirteenth = twelfth.shift(DOWN, 1).shift(RIGHT, 1)
        val fourteenth = thirteenth.shift(UP, 1)
        val fifteenth = fourteenth.shift(RIGHT, 1)
        return listOf(
            first, second, third, fourth, fifth, sixth, seventh, eighth, ninth, tenth,
            eleventh, twelfth, thirteenth, fourteenth, fifteenth
        )
    }
}