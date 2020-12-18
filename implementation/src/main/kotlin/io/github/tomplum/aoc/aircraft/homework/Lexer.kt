package io.github.tomplum.aoc.aircraft.homework

import kotlin.math.exp

class Lexer {
    fun read(data: String): List<Expression> {
        val prepared = data.replace(" ", "")
        return findNestedExpressions(prepared, mutableListOf())
    }

    private fun findNestedExpressions(data: String, expressions: MutableList<Expression>): List<Expression> {
        val tokens = mutableListOf<Token>()
        var i = 0
        while (i < data.length) {
            val token = data[i]
            when {
                token.isDigit() -> tokens.add(Number(token.toString().toInt()))
                token == '(' -> {
                    val nested = findNestedExpressions(data.substring(i + 1), mutableListOf())
                    tokens.addAll(nested)
                    i = data.indexOfFirst { it == ')' } + 1
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

}