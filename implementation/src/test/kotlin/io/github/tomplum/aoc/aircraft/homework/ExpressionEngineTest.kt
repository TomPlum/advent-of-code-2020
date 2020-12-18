package io.github.tomplum.aoc.aircraft.homework

import assertk.assertThat
import assertk.assertions.isEqualTo
import io.github.tomplum.aoc.aircraft.homework.strategy.BasicMath
import io.github.tomplum.aoc.input.TestInputReader
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class ExpressionEngineTest {
    @Nested
    inner class BasicMathExamples {
        @Test
        fun example() {
            val input = TestInputReader.read<String>("homework/example.txt")
            val expressions = Lexer().read(input.value)
            val engine = ExpressionEngine(BasicMath())
            assertThat(engine.sum(expressions)).isEqualTo(26386)
        }
    }
}