package io.github.tomplum.aoc.aircraft.homework

import assertk.assertThat
import assertk.assertions.isEqualTo
import io.github.tomplum.aoc.aircraft.homework.Operator.*
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class ExpressionTest {
    @Nested
    inner class Solve {
        @Test
        fun multiply() {
            val expression = Expression(listOf(Number(2), MULTIPLY, Number(3)))
            val result = expression.solve()
            assertThat(result.value).isEqualTo(6)
        }

        @Test
        fun addition() {
            val expression = Expression(listOf(Number(2), ADD, Number(3)))
            val result = expression.solve()
            assertThat(result.value).isEqualTo(5)
        }

        @Test
        fun nested() {
            val expression = Expression(
                listOf(Number(1), ADD, Expression(listOf(Number(2), MULTIPLY, Number(3))))
            )
            val result = expression.solve()
            assertThat(result.value).isEqualTo(7)
        }
    }
}