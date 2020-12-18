package io.github.tomplum.aoc.aircraft.homework

import assertk.assertThat
import assertk.assertions.isEqualTo
import io.github.tomplum.aoc.aircraft.homework.Operator.*
import io.github.tomplum.aoc.aircraft.homework.strategy.BasicMath
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class ExpressionTest {
    @Nested
    inner class SolveBasicMath {
        @Test
        fun multiply() {
            val expression = Expression(listOf(Number(2), MULTIPLY, Number(3)))
            val result = expression.solve(BasicMath())
            assertThat(result.value).isEqualTo(6)
        }

        @Test
        fun addition() {
            val expression = Expression(listOf(Number(2), ADD, Number(3)))
            val result = expression.solve(BasicMath())
            assertThat(result.value).isEqualTo(5)
        }

        @Test
        fun nested() {
            val expression = Expression(
                listOf(Number(1), ADD, Expression(listOf(Number(2), MULTIPLY, Number(3))))
            )
            val result = expression.solve(BasicMath())
            assertThat(result.value).isEqualTo(7)
        }
    }
}