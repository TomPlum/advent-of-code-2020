package io.github.tomplum.aoc.ferry.raft

/**
 * A list of ingredients written an unknown language.
 * Each entry contains a list of foods with a list of allergens of which some of the foods have.
 * @param entries A list of all the food-allergen entries.
 */
data class IngredientList(private val entries: List<FoodEntry>) {
    /**
     * Gets a list of all the foods listed in the [entries].
     * @return Only the distinct names of the foods.
     */
    fun getDistinctFoods(): List<String> = getFoods().distinct()

    /**
     * Gets a list of all the allergens and maps it to all the foods that appear in every entry with that allergen.
     * @return A map of allergens and their possible foods.
     */
    fun getAllergenicFoods(): Map<String, List<String>> {
        return getDistinctAllergens().map { allergen ->
            val relevantFoods = getEntriesWithAllergen(allergen).map { it.foods }
            val foods = getDistinctFoods().filter { food -> relevantFoods.all { foodList -> foodList.contains(food) } }
            allergen to foods
        }.toMap()
    }

    /**
     * Counts all the times the given [food] name appears in any of the [entries].
     * @param food The name of the food to find references for.
     * @return The sum of all the references to [food].
     */
    fun getReferenceCount(food: String) = getFoods().count { it == food }

    /**
     * Compiles a list of all the food name references in all the [entries].
     * @return A list of all the food names, including any duplicates.
     */
    private fun getFoods(): List<String> = entries.flatMap { entry -> entry.foods }

    /**
     * Compiles a list of all the allergen name references in all the [entries].
     * @return A list of all the distinct allergen names.
     */
    private fun getDistinctAllergens(): Set<String> = entries.flatMap { it.allergens }.distinct().toSet()

    /**
     * Finds all the [entries] that contain the given [allergen].
     * @param allergen The name of the allergen to filter by.
     * @return A list of all the entries containing the [allergen].
     */
    private fun getEntriesWithAllergen(allergen: String): List<FoodEntry> = entries.filter { entry ->
        entry.allergens.contains(allergen)
    }
}