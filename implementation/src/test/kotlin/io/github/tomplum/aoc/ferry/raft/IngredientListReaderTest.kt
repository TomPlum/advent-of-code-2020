package io.github.tomplum.aoc.ferry.raft

import assertk.assertThat
import assertk.assertions.isEqualTo
import io.github.tomplum.aoc.input.TestInputReader
import org.junit.jupiter.api.Test

class IngredientListReaderTest {
    @Test
    fun example() {
        val input = TestInputReader.read<String>("ferry/raft/example.txt")
        val list = IngredientListReader.read(input.value)
        assertThat(list).isEqualTo(getExpectedIngredientList())
    }

    private fun getExpectedIngredientList(): IngredientList {
        val entry1 = FoodEntry(listOf("mxmxvkd", "kfcds", "sqjhc", "nhms"), listOf("dairy", "fish"))
        val entry2 = FoodEntry(listOf("trh", "fvjkl", "sbzzf", "mxmxvkd"), listOf("dairy"))
        val entry3 = FoodEntry(listOf("sqjhc", "fvjkl"), listOf("soy"))
        val entry4 = FoodEntry(listOf("sqjhc", "mxmxvkd", "sbzzf"), listOf("fish"))
        return IngredientList(listOf(entry1, entry2, entry3, entry4))
    }
}