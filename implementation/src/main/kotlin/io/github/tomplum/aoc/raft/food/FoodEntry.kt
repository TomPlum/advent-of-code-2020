package io.github.tomplum.aoc.raft.food

/**
 * A single entry in an [IngredientList].
 * - A food can have exactly zero or one allergy.
 * - An allergenic food may or may not have its allergy listed in [allergens].
 *
 * @param foods A list of names of foods.
 * @param allergens A list of allergens of which some of the foods contain.
 */
data class FoodEntry(val foods: List<String>, val allergens: List<String>)