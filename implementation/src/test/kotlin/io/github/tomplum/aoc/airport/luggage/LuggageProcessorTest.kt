package io.github.tomplum.aoc.airport.luggage

import assertk.assertThat
import assertk.assertions.isEqualTo
import io.github.tomplum.aoc.input.TestInputReader
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class LuggageProcessorTest {
    @Nested
    inner class BagColoursContaining {
        @Test
        fun example() {
            val data = TestInputReader().readInputAsString("luggage/example-rules.txt")
            val luggage = LuggageRuleParser.parse(data.value)
            val processor = LuggageProcessor(luggage)
            assertThat(processor.getBagColoursContaining("shiny gold")).isEqualTo(4)
        }
    }

    @Nested
    inner class BagsContainedIn {
        @Test
        fun exampleOne() {
            val data = TestInputReader().readInputAsString("luggage/example-rules.txt")
            val luggage = LuggageRuleParser.parse(data.value)
            val processor = LuggageProcessor(luggage)
            assertThat(processor.getBagRequirement("shiny gold")).isEqualTo(32)
        }

        @Test
        fun exampleTwo() {
            val data = TestInputReader().readInputAsString("luggage/example-rules-2.txt")
            val luggage = LuggageRuleParser.parse(data.value)
            val processor = LuggageProcessor(luggage)
            assertThat(processor.getBagRequirement("shiny gold")).isEqualTo(126)
        }
    }
}