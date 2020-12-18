package io.github.tomplum.aoc.aircraft.homework

import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.Test

class ExpressionEngineTest {
    @Test
    fun exampleOne() {
        val expression = Lexer().read(listOf("1 + 2 * 3 + 4 * 5 + 6"))
        val engine = ExpressionEngine(expression)
        assertThat(engine.solve()).isEqualTo(71)
    }

    @Test
    fun exampleTwo() {
        val expression = Lexer().read(listOf("2 * 3 + (4 * 5)"))
        val engine = ExpressionEngine(expression)
        assertThat(engine.solve()).isEqualTo(26)
    }

    @Test
    fun exampleThree() {
        val expression = Lexer().read(listOf("5 + (8 * 3 + 9 + 3 * 4 * 3)"))
        val engine = ExpressionEngine(expression)
        assertThat(engine.solve()).isEqualTo(437)
    }

    @Test
    fun exampleFour() {
        val expression = Lexer().read(listOf("5 * 9 * (7 * 3 * 3 + 9 * 3 + (8 + 6 * 4))"))
        val engine = ExpressionEngine(expression)
        assertThat(engine.solve()).isEqualTo(12240)
    }

    @Test
    fun exampleFive() {
        val expression = Lexer().read(listOf("((2 + 4 * 9) * (6 + 9 * 8 + 6) + 6) + 2 + 4 * 2"))
        val engine = ExpressionEngine(expression)
        assertThat(engine.solve()).isEqualTo(13632)
    }
}