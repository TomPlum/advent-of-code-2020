package io.github.tomplum.aoc.aircraft.homework.types

import assertk.assertThat
import assertk.assertions.isEqualTo
import io.github.tomplum.aoc.aircraft.homework.strategy.OperationOrderStrategy
import io.github.tomplum.aoc.aircraft.homework.types.Operator.ADD
import io.github.tomplum.aoc.aircraft.homework.types.Operator.MULTIPLY
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.verify
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(MockKExtension::class)
class ExpressionTest {
    @Nested
    inner class Solve {
        @MockK(relaxed = true)
        lateinit var strategy: OperationOrderStrategy

        @Test
        fun solve() {
            val tokens = listOf(Number(2), MULTIPLY, Number(12))
            Expression(tokens).solve(strategy)
            verify(exactly = 1) { strategy.resolve(tokens) }
        }
    }

    @Nested
    inner class GetLength {
        @Test
        fun threeTokens() {
            assertThat(Expression(listOf(Number(2), MULTIPLY, Number(12))).getLength()).isEqualTo(3)
        }

        @Test
        fun fiveTokens() {
            assertThat(Expression(listOf(Number(2), MULTIPLY, Number(12), ADD, Number(1))).getLength()).isEqualTo(5)
        }
    }

    @Nested
    inner class ToString {
        @Test
        fun simple() {
            assertThat(Expression(listOf(Number(2), MULTIPLY, Number(12))).toString()).isEqualTo("2 * 12")
        }

        @Test
        fun nested() {
            assertThat(Expression(listOf(
                Number(1), ADD, Expression(listOf(Number(2), MULTIPLY, Number(3))), ADD,
                Expression(listOf(Number(4), MULTIPLY, Expression(listOf(Number(5), ADD, Number(6))))),
            )).toString()).isEqualTo("1 + (2 * 3) + (4 * (5 + 6))")
        }
    }
}