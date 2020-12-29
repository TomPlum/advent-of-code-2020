package io.github.tomplum.aoc.aircraft.homework

import io.github.tomplum.aoc.aircraft.homework.types.Expression
import io.github.tomplum.aoc.aircraft.homework.types.Number
import io.github.tomplum.aoc.aircraft.homework.types.Operator
import io.github.tomplum.aoc.aircraft.homework.types.Token

/**
 * Parses mathematical symbols and creates [Expression] objects.
 */
class Lexer {
    fun read(data: List<String>): List<Expression> {
        return data.map { expression ->
            val prepared = expression.replace(" ", "")
            findNestedExpressions(prepared, mutableListOf()).first()
        }
    }

    private fun findNestedExpressions(data: String, expressions: MutableList<Expression>): List<Expression> {
        val tokens = mutableListOf<Token>()
        var i = 0
        while (i < data.length) {
            val token = data[i]
            when {
                token.isDigit() -> tokens.add(Number(token.toString().toLong()))
                token == '(' -> {
                    val nested = findNestedExpressions(data.substring(i + 1), mutableListOf())
                    tokens.addAll(nested)

                    i = data.getClosingParenthesisIndex(i) + 1

                    if (tokens.sumBy { it.getLength() } == data.replace(")", "").replace("(", "").count()) {
                        expressions.add(Expression(tokens))
                        return expressions
                    }
                    continue
                }
                token == ')' -> {
                    expressions.add(Expression(tokens))
                    break
                }
                else -> when(token) {
                    '+' -> tokens.add(Operator.ADD)
                    '*' -> tokens.add(Operator.MULTIPLY)
                    else -> throw IllegalArgumentException("Invalid Operator $token")
                }
            }
            i++
        }

        if (i == data.length) {
            expressions.add(Expression(tokens))
            return expressions
        }
        return expressions
    }

    private fun String.getClosingParenthesisIndex(i: Int): Int {
        var open = 0
        substring(i).forEachIndexed { j, token ->
            when(token) {
                '(' -> open++
                ')' -> {
                    if (open == 1) return j + i else open--
                }
            }
        }
        throw IllegalArgumentException("Cannot find closing parenthesis for i=$i in ${substring(i)}")
    }

}