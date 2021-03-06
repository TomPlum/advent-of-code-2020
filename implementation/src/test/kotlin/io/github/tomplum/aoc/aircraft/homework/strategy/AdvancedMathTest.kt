package io.github.tomplum.aoc.aircraft.homework.strategy

import assertk.assertThat
import assertk.assertions.isEqualTo
import io.github.tomplum.aoc.aircraft.homework.Lexer
import org.junit.jupiter.api.Test

class AdvancedMathTest {
    @Test
    fun exampleOne() {
        val expression = Lexer().read(listOf("1 + 2 * 3 + 4 * 5 + 6")).first()
        val result = AdvancedMath().resolve(expression.tokens)
        assertThat(result.value).isEqualTo(231)
    }

    @Test
    fun exampleTwo() {
        val expression = Lexer().read(listOf("1 + (2 * 3) + (4 * (5 + 6))")).first()
        val result = AdvancedMath().resolve(expression.tokens)
        assertThat(result.value).isEqualTo(51)
    }

    @Test
    fun exampleThree() {
        val expression = Lexer().read(listOf("2 * 3 + (4 * 5)")).first()
        val result = AdvancedMath().resolve(expression.tokens)
        assertThat(result.value).isEqualTo(46)
    }

    @Test
    fun exampleFour() {
        val expression = Lexer().read(listOf("5 + (8 * 3 + 9 + 3 * 4 * 3)")).first()
        val result = AdvancedMath().resolve(expression.tokens)
        assertThat(result.value).isEqualTo(1445)
    }

    @Test
    fun exampleFive() {
        val expression = Lexer().read(listOf("5 * 9 * (7 * 3 * 3 + 9 * 3 + (8 + 6 * 4))")).first()
        val result = AdvancedMath().resolve(expression.tokens)
        assertThat(result.value).isEqualTo(669060)
    }

    @Test
    fun exampleSix() {
        val expression = Lexer().read(listOf("((2 + 4 * 9) * (6 + 9 * 8 + 6) + 6) + 2 + 4 * 2")).first()
        val result = AdvancedMath().resolve(expression.tokens)
        assertThat(result.value).isEqualTo(23340)
    }
}