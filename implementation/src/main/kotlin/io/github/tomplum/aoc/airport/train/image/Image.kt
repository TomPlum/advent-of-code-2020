package io.github.tomplum.aoc.airport.train.image

import io.github.tomplum.libs.math.Direction.*
import io.github.tomplum.libs.math.map.AdventMap2D
import io.github.tomplum.libs.math.point.Point2D
import kotlin.math.abs

class Image: AdventMap2D<ImageTileData>() {

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
                            val tileData = section.tile.getData(sectionX, sectionY)
                            image.addTile(Point2D(x, y), tileData)
                            x++
                        }
                    }
                    x = 0
                    y++
                }
            }

            return image
        }
    }

    fun getOrientations(): List<Image> {
        val flipped = listOf(xFlip(), yFlip(), xFlip().yFlip())
        val rotated = flipped.flatMap { image ->
            listOf(
                image.rotateClockwise(90), //90
                image.rotateClockwise(90).rotateClockwise(90), //180
                image.rotateClockwise(90).rotateClockwise(90).rotateClockwise(90), //270
            )
        }

        return (flipped + rotated).distinct()
    }

    fun getHabitatWaterRoughness(): Int = filterTiles { tile -> tile.isWave() }.count()

    fun locateSeaMonsters() {
        (1 until yMax()!!).forEach { y ->
            (0..xMax()!! - 19).forEach { x ->
                val position = Point2D(x, y)
                val positions = position.getSeaMonsterPositionCandidates()
                val isMatch = filterPoints(positions).values.all { tile -> tile.isWave() }
                if (isMatch) {
                    positions.forEach { pos ->
                        addTile(pos, ImageTileData('O'))
                    }
                }
            }
        }
    }

    fun containsSeaMonsters(): Boolean = filterTiles { it.isSeaMonster() }.count() > 0

    private fun xFlip(): Image = data.entries.fold(Image()) { flipped, (pos, tile) ->
        val posFlipped = Point2D(xMax()!! - pos.x, pos.y)
        flipped.apply { addTile(posFlipped, tile) }
    }


    private fun yFlip(): Image = data.entries.fold(Image()) { flipped, (pos, tile) ->
        val posFlipped = Point2D(pos.x, yMax()!! - pos.y)
        flipped.apply { addTile(posFlipped, tile) }
    }

    private fun rotateClockwise(degrees: Int): Image = data
        .entries.fold(Image()) { rotated, (pos, tile) ->
            val yInverted = Point2D(pos.x, -pos.y) //Convert regular cartesian -> single quadrant (y is down)
            val posRotated = yInverted.rotateAbout(Point2D.origin(), degrees) //Rotate about origin
            val posShifted = posRotated.shift(RIGHT, xMax()!!) //Shift back into our quadrant
            val yCorrected = Point2D(posShifted.x, abs(posShifted.y)) //Flip y back to our quadrant
            rotated.apply { addTile(yCorrected, tile) }
        }

    private fun Point2D.getSeaMonsterPositionCandidates(): Set<Point2D> {
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
        return setOf(first, second, third, fourth, fifth, sixth, seventh, eighth, ninth, tenth,
            eleventh, twelfth, thirteenth, fourteenth, fifteenth)
    }
}