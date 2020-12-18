package io.github.tomplum.aoc.aircraft.homework

import assertk.assertThat
import assertk.assertions.isEqualTo
import io.github.tomplum.aoc.aircraft.homework.strategy.BasicMath
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class ExpressionEngineTest {
    @Nested
    inner class BasicMathExamples {
        @Test
        fun exampleOne() {
            val expressions = Lexer().read(listOf("1 + 2 * 3 + 4 * 5 + 6"))
            val engine = ExpressionEngine(BasicMath())
            assertThat(engine.sum(expressions)).isEqualTo(71)
        }

        @Test
        fun exampleTwo() {
            val expressions = Lexer().read(listOf("2 * 3 + (4 * 5)"))
            val engine = ExpressionEngine(BasicMath())
            assertThat(engine.sum(expressions)).isEqualTo(26)
        }

        @Test
        fun exampleThree() {
            val expressions = Lexer().read(listOf("5 + (8 * 3 + 9 + 3 * 4 * 3)"))
            val engine = ExpressionEngine(BasicMath())
            assertThat(engine.sum(expressions)).isEqualTo(437)
        }

        @Test
        fun exampleFour() {
            val expressions = Lexer().read(listOf("5 * 9 * (7 * 3 * 3 + 9 * 3 + (8 + 6 * 4))"))
            val engine = ExpressionEngine(BasicMath())
            assertThat(engine.sum(expressions)).isEqualTo(12240)
        }

        @Test
        fun exampleFive() {
            val expressions = Lexer().read(listOf("((2 + 4 * 9) * (6 + 9 * 8 + 6) + 6) + 2 + 4 * 2"))
            val engine = ExpressionEngine(BasicMath())
            assertThat(engine.sum(expressions)).isEqualTo(13632)
        }
    }
}