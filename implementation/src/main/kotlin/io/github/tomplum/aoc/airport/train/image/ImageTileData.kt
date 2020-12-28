package io.github.tomplum.aoc.airport.train.image

import io.github.tomplum.libs.math.map.MapTile

/**
 * A single pixel in an [ImageTile] which is ultimately part of a greater [Image].
 * @param chroming The colour of the pixel.
 */
data class ImageTileData(private val chroming: Char): MapTile<Char>(chroming) {

    /**
     * Checks if the pixel is a wave.
     * @return true if is a wave, else false.
     */
    fun isWave(): Boolean = chroming == '#'

    /**
     * Checks if the pixel is part of a sea monster.
     * @return true if is a sea monster, else false.
     */
    fun isSeaMonster(): Boolean = chroming == 'O'

    /**
     * Converts wave (#) and regular sea (.) pixels into a 1 and 0 respectively.
     * Allows [ImageTile] edges to be represented as binary.
     * @return The binary representation of the pixel.
     */
    fun toBinary(): Char = if (chroming == '#') '1' else '0'

    /**
     * Represents the pixels chroming as a String.
     * @return The pixels chroming.
     */
    override fun toString(): String = chroming.toString()
}