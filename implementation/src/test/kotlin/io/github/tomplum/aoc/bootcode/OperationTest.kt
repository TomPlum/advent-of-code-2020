package io.github.tomplum.aoc.bootcode

import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.EmptySource
import org.junit.jupiter.params.provider.ValueSource

class OperationTest {
    @Nested
    inner class FromString {
        @Test
        fun noOperation() {
            assertThat(Operation.fromString("nop")).isEqualTo(Operation.NO_OPERATION)
        }

        @Test
        fun jump() {
            assertThat(Operation.fromString("jmp")).isEqualTo(Operation.JUMP)
        }

        @Test
        fun accumulate() {
            assertThat(Operation.fromString("acc")).isEqualTo(Operation.ACCUMULATE)
        }

        @ParameterizedTest
        @EmptySource
        @ValueSource(strings = ["nooperation", "jump", "accumulate", "acc-", "jump "])
        fun invalidString(value: String) {
            val e = assertThrows<IllegalArgumentException> { Operation.fromString(value) }
            assertThat(e.message).isEqualTo("Invalid Operation $value")
        }

    }
}