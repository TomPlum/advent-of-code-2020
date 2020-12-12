package io.github.tomplum.aoc.ferry.navigation

import assertk.assertThat
import assertk.assertions.isEqualTo
import io.github.tomplum.aoc.input.TestInputReader
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class NavigationSystemTest {
    @Nested
    inner class Navigate {
        @Test
        fun example() {
            val instructions = TestInputReader.read<String>("ferry/navigation/instructions.txt")
            val navigationSystem = NavigationSystem(instructions.value)
            assertThat(navigationSystem.navigate()).isEqualTo(25)
        }
    }

    @Nested
    inner class NavigateViaWaypoint {
        @Test
        fun example() {
            val instructions = TestInputReader.read<String>("ferry/navigation/instructions.txt")
            val navigationSystem = NavigationSystem(instructions.value)
            assertThat(navigationSystem.navigateViaWaypoint()).isEqualTo(286)
        }
    }
}