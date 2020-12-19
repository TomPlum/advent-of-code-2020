package io.github.tomplum.aoc.forest.toboggan

import assertk.assertThat
import assertk.assertions.isEqualTo
import io.github.tomplum.aoc.forest.toboggan.SlopeTrajectory
import io.github.tomplum.libs.math.Point2D
import org.junit.jupiter.api.Test

class SlopeTrajectoryTest {
    @Test
    fun apply() {
        val slopeTrajectory = SlopeTrajectory(3, 1)
        val translatedPosition = slopeTrajectory.apply(Point2D(1, 1))
        assertThat(translatedPosition).isEqualTo(Point2D(4, 2))
    }
}