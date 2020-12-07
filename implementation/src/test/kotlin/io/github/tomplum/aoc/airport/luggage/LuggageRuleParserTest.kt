package io.github.tomplum.aoc.airport.luggage

import io.github.tomplum.aoc.input.TestInputReader
import org.junit.jupiter.api.Test

class LuggageRuleParserTest {
    @Test
    fun example() {
        val input = TestInputReader().readInputAsString("luggage/example-rules.txt")
        LuggageRuleParser.parse(input.value)
    }
}