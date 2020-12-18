package io.github.tomplum.aoc.aircraft.homework

import assertk.assertThat
import assertk.assertions.containsOnly
import io.github.tomplum.aoc.aircraft.homework.Operator.*
import org.junit.jupiter.api.Test

class LexerTest {
    @Test
    fun example() {
        val expressions = Lexer().read("1 + 2 * 3 + 4 * 5 + 6")
        val expected = Expression(listOf(
            Number(1), ADD, Number(2), MULTIPLY, Number(3), ADD, Number(4), MULTIPLY, Number(5), ADD, Number(6)
        ))
        assertThat(expressions).containsOnly(expected)
    }

    @Test
    fun exampleWithParentheses() {
        val expressions = Lexer().read("1 + (2 * 3) + (4 * (5 + 6))")
        val expected = Expression(listOf(
            Number(1), ADD, Expression(listOf(Number(2), MULTIPLY, Number(3))), ADD,
            Expression(listOf(Number(4), MULTIPLY, Expression(listOf(Number(5), ADD, Number(6))))),
        ))
        assertThat(expressions).containsOnly(expected)
    }
}