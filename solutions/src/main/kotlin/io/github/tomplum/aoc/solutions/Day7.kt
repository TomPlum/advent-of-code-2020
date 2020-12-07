package io.github.tomplum.aoc.solutions

import io.github.tomplum.aoc.Solution
import io.github.tomplum.aoc.airport.luggage.LuggageProcessor
import io.github.tomplum.aoc.airport.luggage.LuggageRuleParser
import io.github.tomplum.libs.input.Day
import io.github.tomplum.libs.input.InputReader

class Day7 : Solution<Int> {
    private val input = InputReader.read<String>(Day(7))
    private val luggage = LuggageRuleParser.parse(input.value)

    override fun part1(): Int {
        return LuggageProcessor(luggage).getBagColoursContaining("shiny gold")
    }

    override fun part2(): Int {
        return LuggageProcessor(luggage).getBagRequirement("shiny gold")
    }
}