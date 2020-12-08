package io.github.tomplum.aoc.airport.customs

import assertk.assertThat
import assertk.assertions.isEqualTo
import io.github.tomplum.aoc.input.TestInputReader
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class QuestionnaireTest {
    @Nested
    inner class YesQuestionCount {
        @Test
        fun example() {
            val input = TestInputReader().readInputAsString("customs/example-answers.txt").asSingleString()
            val count = Questionnaire(input).getYesQuestionCount()
            assertThat(count).isEqualTo(11)
        }

        @Test
        fun emptyAnswers() {
            assertThat(Questionnaire("").getYesQuestionCount()).isEqualTo(0)
        }
    }

    @Nested
    inner class CommonYesQuestionCount {
        @Test
        fun example() {
            val input = TestInputReader().readInputAsString("customs/example-answers.txt").asSingleString()
            val count = Questionnaire(input).getCommonYesQuestionCount()
            assertThat(count).isEqualTo(6)
        }

        @Test
        fun emptyAnswers() {
            assertThat(Questionnaire("").getCommonYesQuestionCount()).isEqualTo(0)
        }
    }
}
