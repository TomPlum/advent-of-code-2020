package io.github.tomplum.aoc.raft.food

import assertk.assertThat
import assertk.assertions.isEqualTo
import io.github.tomplum.aoc.input.TestInputReader
import io.github.tomplum.aoc.raft.food.AllergenAssessment
import io.github.tomplum.aoc.raft.food.IngredientListReader
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class AllergenAssessmentTest {
    @Nested
    inner class GetNonAllergenicIngredients {
        @Test
        fun example() {
            val input = TestInputReader.read<String>("ferry/raft/example.txt")
            val ingredients = IngredientListReader.read(input.value)
            assertThat(AllergenAssessment(ingredients).getNonAllergenicFoods()).isEqualTo(5)
        }
    }

    @Nested
    inner class GetCanonicalDangerousIngredientList {
        @Test
        fun example() {
            val input = TestInputReader.read<String>("ferry/raft/example.txt")
            val ingredients = IngredientListReader.read(input.value)
            val list = AllergenAssessment(ingredients).getCanonicalDangerousIngredientList()
            assertThat(list).isEqualTo("mxmxvkd,sqjhc,fvjkl")
        }
    }
}