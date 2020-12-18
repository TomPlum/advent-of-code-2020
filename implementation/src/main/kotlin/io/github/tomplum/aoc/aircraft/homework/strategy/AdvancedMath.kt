package io.github.tomplum.aoc.aircraft.homework.strategy

import io.github.tomplum.aoc.aircraft.homework.Expression
import io.github.tomplum.aoc.aircraft.homework.Number
import io.github.tomplum.aoc.aircraft.homework.Operator
import io.github.tomplum.aoc.aircraft.homework.Token

class AdvancedMath: OperationOrderStrategy {
    override fun resolve(expressionTokens: List<Token>): Number {
        val flattened = expressionTokens.map { token ->
            if (token is Expression) token.solve(this) else token
        }

        val start = flattened.windowed(3, 2).find { (it[1] as Operator) == Operator.ADD }

        val additions = flattened.windowed(3, 2).filter { (it[1] as Operator) == Operator.ADD }

        if (flattened.contains(Operator.ADD)) {
            if (!flattened.contains(Operator.MULTIPLY)) {
                return flattened.filterIsInstance<Number>()
                    .reduce { sum, number -> Operator.ADD.apply(sum, number) }
            }
            val resolvedAdditions = mutableListOf<Token>()
            var i = 0
            while(i < flattened.size) {
                val token = flattened[i]
                if (token == Operator.MULTIPLY) {
                    resolvedAdditions.add(token)
                    i++
                } else {
                    val additionExpression = flattened.subList(i, flattened.size).takeWhile { it == Operator.ADD || it is Number }
                    if (additionExpression.size == 1) {
                        resolvedAdditions.add(additionExpression.first())
                        i++
                    } else {
                        resolvedAdditions.add(BasicMath().resolve(additionExpression))
                        i += additionExpression.size
                    }
                }
            }

            //If there were no multiplications and all additions were resolved to numbers, then sum
            if (resolvedAdditions.all { it is Number }) {
                return resolvedAdditions.filterIsInstance<Number>()
                    .reduce { sum, number -> Operator.ADD.apply(sum, number) }
            }

            //If there are still multiplication interspersed after resolving the additions
            return BasicMath().resolve(resolvedAdditions)
        }

        return BasicMath().resolve(flattened)
    }
}