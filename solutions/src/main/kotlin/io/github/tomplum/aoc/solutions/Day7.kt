package io.github.tomplum.aoc.solutions

import io.github.tomplum.aoc.Solution
import io.github.tomplum.aoc.airport.luggage.LuggageProcessor
import io.github.tomplum.aoc.airport.luggage.LuggageRuleParser
import io.github.tomplum.libs.input.Day
import io.github.tomplum.libs.input.InputReader

class Day7 : Solution<Int> {
    override fun part1(): Int {
        val input = InputReader.read<String>(Day(7))
        val luggage = LuggageRuleParser.parse(input.value)
        return LuggageProcessor(luggage).process("shiny gold")
    }
}