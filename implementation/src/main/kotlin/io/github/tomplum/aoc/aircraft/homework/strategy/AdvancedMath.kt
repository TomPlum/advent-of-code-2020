package io.github.tomplum.aoc.aircraft.homework.strategy

import io.github.tomplum.aoc.aircraft.homework.types.Expression
import io.github.tomplum.aoc.aircraft.homework.types.Number
import io.github.tomplum.aoc.aircraft.homework.types.Operator
import io.github.tomplum.aoc.aircraft.homework.types.Token

class AdvancedMath: OperationOrderStrategy {
    override fun resolve(expressionTokens: List<Token>): Number {
        val simplified = expressionTokens.map { token ->
            if (token is Expression) token.solve(this) else token
        }

        if (simplified.contains(Operator.ADD)) {
            //Resolves the additions leaving only the sums and/or any remaining multiplications
            val resolvedAdditions = mutableListOf<Token>()
            var i = 0
            while(i < simplified.size) {
                val token = simplified[i]
                if (token == Operator.MULTIPLY) {
                    resolvedAdditions.add(token)
                    i++
                } else {
                    val additionExpression = simplified.subList(i, simplified.size)
                        .takeWhile { it == Operator.ADD || it is Number }
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

        //If there are no additions, just resolve with the basic strategy
        return BasicMath().resolve(simplified)
    }
}