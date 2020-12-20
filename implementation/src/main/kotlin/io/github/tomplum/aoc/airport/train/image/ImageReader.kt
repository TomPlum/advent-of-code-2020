package io.github.tomplum.aoc.airport.train.image

class ImageReader private constructor() {
    companion object {
        fun read(data: String): ImageArray = data.split("\n\n").map { tileData ->
            val id = tileData.split("\n").first().replace("Tile ", "").replace(":", "").toInt()
            val tiles = tileData.split("\n").drop(1)
            ImageTile(id, tiles)
        }.let { tiles -> ImageArray(tiles) }
    }
}