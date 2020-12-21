package io.github.tomplum.aoc.ferry.raft

import assertk.assertThat
import assertk.assertions.isEqualTo
import io.github.tomplum.aoc.input.TestInputReader
import org.junit.jupiter.api.Test

class AllergenAssessmentTest {
    @Test
    fun example() {
        val input = TestInputReader.read<String>("ferry/raft/example.txt")
        val ingredients = IngredientListReader.read(input.value)
        assertThat(AllergenAssessment(ingredients).getNonAllergenicFoods()).isEqualTo(5)
    }
}