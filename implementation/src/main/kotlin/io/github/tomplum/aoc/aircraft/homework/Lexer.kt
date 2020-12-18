package io.github.tomplum.aoc.aircraft.homework

class Lexer {
    fun read(data: String): Expression = data.replace(" ", "")
        .fold(mutableListOf<Token>()) { acc, char ->
            acc.apply {
                add(when {
                    char.isDigit() -> Term(char.toString().toInt())
                    else -> when (char) {
                        '*' -> Operator.MULTIPLY
                        '+' -> Operator.ADD
                        else -> throw IllegalArgumentException("Invalid Operator $char")
                    }
                })
            }
        }.let { Expression(it) }

}