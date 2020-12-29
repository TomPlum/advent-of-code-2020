package io.github.tomplum.aoc.airport.train.image

import io.github.tomplum.libs.math.Direction.*
import io.github.tomplum.libs.math.map.AdventMap2D
import io.github.tomplum.libs.math.point.Point2D
import kotlin.math.abs

/**
 * A complete image assembled from the [ImageTile]s mapped in an [ImageTileMapping].
 * The image may or may not be in the correct orientation.
 * @param width The width of the image, equal to the max ordinate.
 */
class Image(private var width: Int = 0) : AdventMap2D<ImageTileData>() {

    companion object {
        /**
         * Assembles the complete image from the tiles in the given [mapping].
         * Each of the [ImageTile]s in the [ImageTileMapping] maintains its own set of coordinates for each of the
         * [ImageTileData] in its grid. This method ignores the sub-grids and maps them all together as if they
         * were all part of the same grid. The result is the complete image.
         *
         * @param mapping A complete mapping of all unique [ImageTile].
         * @return The build image in the same orientation as the [mapping].
         */
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

    /**
     * Retrieves the 8 possible orientations of the [Image].
     * Each of tile orientations is searched for sea monsters.
     * @return A list of the 8 copies in their new orientations.
     */
    fun getOrientations(): List<Image> {
        val xFlipped = xFlip()
        val flipped = listOf(xFlipped, yFlip(), xFlipped.yFlip())
        val rotated = flipped.flatMap { image ->
            val ninety = image.rotateClockwise90()
            val oneHundredEighty = ninety.rotateClockwise90()
            val twoHundredSeventy = oneHundredEighty.rotateClockwise90()
            listOf(ninety, oneHundredEighty, twoHundredSeventy)
        }

        return (flipped + rotated).distinct().map { image -> image.locateSeaMonsters() }
    }

    /**
     * Determines how rough the waters are in the sea monsters' habitat by counting
     * the number of [ImageTileData] that are waves (#).
     * @return The roughness of the water in the image.
     */
    fun getWaterRoughness(): Int = filterTiles { tile -> tile.isWave() }.count()

    /**
     * Checks to see if the image in its current orientations contains sea monsters.
     * @return true if sea monsters are present, else false.
     */
    fun containsSeaMonsters(): Boolean = getDataMap().values.any { tile -> tile.isSeaMonster() }

    /**
     * Searches the [Image] for sea monsters.
     * A sea monster looks like the below;
     *
     *                      #
     *    #    ##    ##    ###
     *     #  #  #  #  #  #
     *
     * Sea monsters can only be on wave tiles denoted by (#).
     * Any sea monsters that are found have their tiles replaced with (O).
     * The algorithm searches for the left-most tile of the sea monster tail and therefore
     * narrows down the tile search scope relative to the dimensions of the monster.
     * @return The image with any sea monster tiles marked.
     */
    private fun locateSeaMonsters(): Image {
        (1 until width).forEach { y ->
            (0..width - 19).forEach { x ->
                val position = Point2D(x, y)
                val positions = position.getSeaMonsterPositionCandidates()
                val isMatch = positions.map { pos -> getDataMap()[pos] }.all { tile -> tile?.isWave() ?: false }
                if (isMatch) {
                    positions.forEach { pos -> addTile(pos, ImageTileData('O')) }
                }
            }
        }
        return this
    }

    /**
     * Flips the [Image] about the X-Axis.
     * @return A copy of the image in its new orientation.
     */
    private fun xFlip(): Image = getDataMap().entries.fold(Image(width)) { flipped, (pos, tile) ->
        val posFlipped = Point2D(width - pos.x, pos.y)
        flipped.apply { addTile(posFlipped, tile) }
    }

    /**
     * Flips the [Image] about the Y-Axis.
     * @return A copy of the image in its new orientation.
     */
    private fun yFlip(): Image = getDataMap().entries.fold(Image(width)) { flipped, (pos, tile) ->
        val posFlipped = Point2D(pos.x, width - pos.y)
        flipped.apply { addTile(posFlipped, tile) }
    }

    /**
     * Rotates the [Image] 90 degrees about the logical centre of itself.
     * This is achieved by rotating the points about the origin and shifting them back to their original quadrant.
     * @return A copy of the image in its new orientation.
     */
    private fun rotateClockwise90(): Image = getDataMap().entries.fold(Image(width)) { rotated, (pos, tile) ->
        val yInverted = Point2D(pos.x, -pos.y) //Convert regular cartesian -> single quadrant (y is down)
        val posRotated = yInverted.rotateAbout(Point2D.origin(), 90) //Rotate about origin
        val posShifted = posRotated.shift(RIGHT, width) //Shift back into our quadrant
        val yCorrected = Point2D(posShifted.x, abs(posShifted.y)) //Flip y back to our quadrant
        rotated.apply { addTile(yCorrected, tile) }
    }

    /**
     * Retrieves all the points on the shape of a sea monster relative to the given [Point2D].
     * Assumes that this point is the left-most tile of the monsters tail.
     * @return A list of tiles that would be on the sea monster if this tile is the tail.
     */
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