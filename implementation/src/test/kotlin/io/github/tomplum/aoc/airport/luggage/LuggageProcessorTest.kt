package io.github.tomplum.aoc.airport.luggage

import assertk.assertThat
import assertk.assertions.isEqualTo
import io.github.tomplum.aoc.input.TestInputReader
import org.junit.jupiter.api.Test

class LuggageProcessorTest {
    @Test
    fun example() {
        val data = TestInputReader().readInputAsString("luggage/example-rules.txt")
        val luggage = LuggageRuleParser.parse(data.value)
        val processor = LuggageProcessor(luggage)
        assertThat(processor.process("shiny gold")).isEqualTo(4)
    }
}