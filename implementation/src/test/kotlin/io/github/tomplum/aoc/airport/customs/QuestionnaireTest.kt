package io.github.tomplum.aoc.airport.customs

import assertk.assertThat
import assertk.assertions.isEqualTo
import io.github.tomplum.aoc.input.TestInputReader
import org.junit.jupiter.api.Test

class QuestionnaireTest {
    @Test
    fun getCorrectQuestionCountExample() {
        val input = TestInputReader().readInputAsString("customs/example-answers.txt").asSingleString()
        val count = Questionnaire(input).getCorrectQuestionCount()
        assertThat(count).isEqualTo(11)
    }

    @Test
    fun getCommonCorrectQuestionCountExample() {
        val input = TestInputReader().readInputAsString("customs/example-answers.txt").asSingleString()
        val count = Questionnaire(input).getCommonCorrectQuestionCount()
        assertThat(count).isEqualTo(6)
    }
}
