package io.github.tomplum.aoc.airport.train.image

/**
 * Reads the image tile data input a produces a list of [ImageTile] objects.
 */
class ImageTileReader private constructor() {
    companion object {
        fun read(data: String): List<ImageTile> = data.split("\n\n").map { tileData ->
            val id = tileData.split("\n").first().replace("Tile ", "").replace(":", "").toInt()
            val tiles = tileData.split("\n").drop(1)
            ImageTile.fromData(id, tiles)
        }
    }
}