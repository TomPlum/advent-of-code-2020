package io.github.tomplum.aoc.aircraft.homework.strategy

import io.github.tomplum.aoc.aircraft.homework.types.Expression
import io.github.tomplum.aoc.aircraft.homework.types.Number
import io.github.tomplum.aoc.aircraft.homework.types.Operator
import io.github.tomplum.aoc.aircraft.homework.types.Token

class BasicMath : OperationOrderStrategy {
    override fun resolve(expressionTokens: List<Token>): Number {
        val flattened = expressionTokens.map { token ->
            if (token is Expression) token.solve(this) else token
        }

        val start = flattened.take(3).let { (it[1] as Operator).apply(it[0] as Number, it[2] as Number) }

        return flattened.drop(3).windowed(2, 2).fold(start) { sum, tokens ->
            val operator = tokens[0] as Operator
            val t2 = tokens[1] as Number
            operator.apply(sum, t2)
        }
    }
}