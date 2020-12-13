package io.github.tomplum.aoc.bus

import assertk.assertThat
import assertk.assertions.isEqualTo
import assertk.assertions.isFalse
import assertk.assertions.isTrue
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class BusTest {
    @Nested
    inner class IsOutOfService {
        @ParameterizedTest
        @ValueSource(strings = ["1", "0", "45", "23"])
        fun inService(id: String) {
            assertThat(Bus(id).isOutOfService()).isFalse()
        }

        @Test
        fun outOfService() {
            assertThat(Bus("x").isOutOfService()).isTrue()
        }
    }

    @Nested
    inner class GetValue {
        @Test
        fun inService() {
            assertThat(Bus("14").getID()).isEqualTo(14)
        }

        @Test
        fun outOfService() {
            val e = assertThrows<IllegalStateException> { Bus("x").getID() }
            assertThat(e.message).isEqualTo("This bus is out of service!")
        }
    }
}