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
            val data = TestInputReader.read<String>("ferry/navigation/instructions.txt").value
            val instructions = InstructionParser.parse(data)
            val navigationSystem = NavigationSystem(instructions)
            assertThat(navigationSystem.navigate()).isEqualTo(25)
        }
    }

    @Nested
    inner class NavigateViaWaypoint {
        @Test
        fun example() {
            val data = TestInputReader.read<String>("ferry/navigation/instructions.txt").value
            val instructions = InstructionParser.parse(data)
            val navigationSystem = NavigationSystem(instructions)
            assertThat(navigationSystem.navigateViaWaypoint()).isEqualTo(286)
        }
    }
}