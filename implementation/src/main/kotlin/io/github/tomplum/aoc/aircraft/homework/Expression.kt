package io.github.tomplum.aoc.aircraft.homework

data class Expression(val tokens: List<Token>): Term {
    override fun solve(): Number {
        val flattened = tokens.map { token ->
            if (token is Expression) token.solve() else token
        }

        val start = flattened.take(3).let { (it[1] as Operator).apply(it[0] as Number, it[2] as Number) }

        return flattened.drop(3).windowed(2, 2).fold(start) { sum, tokens ->
            val operator = tokens[0] as Operator
            val t2 = tokens[1] as Number
            operator.apply(sum, t2)
        }
    }

    override fun getLength(): Int = tokens.sumBy { it.getLength() }

    override fun toString(): String = tokens.joinToString(" ") {
        if (it is Expression) "($it)" else it.toString()
    }
}