package io.github.tomplum.aoc.aircraft.homework.strategy

import io.github.tomplum.aoc.aircraft.homework.Number
import io.github.tomplum.aoc.aircraft.homework.Token

interface OperationOrderStrategy {
    fun resolve(expressionTokens: List<Token>): Number
}