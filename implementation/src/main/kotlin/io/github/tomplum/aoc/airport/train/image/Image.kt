package io.github.tomplum.aoc.airport.train.image

import io.github.tomplum.libs.math.map.AdventMap2D
import io.github.tomplum.libs.math.point.Point2D

class Image(mapping: ImageTileMapping): AdventMap2D<ImageTileData>() {
    private val sectionWidth = mapping.getSectionWidth()

    init {
        var y = 0
        mapping.getRows().forEach { row ->
            var x = 0
            (0..sectionWidth).forEach { sectionY ->
                row.forEach { section ->
                    (0..sectionWidth).forEach { sectionX ->
                        val tileData = section.tile.getData(sectionX, sectionY)
                        addTile(Point2D(x, y), tileData)
                        x++
                    }
                }
                x = 0
                y++
            }
        }
    }
}