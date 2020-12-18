package io.github.tomplum.aoc.aircraft.homework.strategy

import io.github.tomplum.aoc.aircraft.homework.types.Number
import io.github.tomplum.aoc.aircraft.homework.types.Token

interface OperationOrderStrategy {
    fun resolve(expressionTokens: List<Token>): Number
}