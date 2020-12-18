package io.github.tomplum.aoc.aircraft.homework

import assertk.assertThat
import assertk.assertions.isEqualTo
import io.github.tomplum.aoc.aircraft.homework.Operator.*
import org.junit.jupiter.api.Test

class LexerTest {
    @Test
    fun example() {
        assertThat(Lexer().read("1 + 2 * 3 + 4 * 5 + 6")).isEqualTo(getExpectedExpression())
    }

    private fun getExpectedExpression(): Expression {
        val tokens = listOf(Term(1), ADD, Term(2), MULTIPLY, Term(3), ADD, Term(4), MULTIPLY, Term(5), ADD, Term(6))
        return Expression(tokens)
    }
}