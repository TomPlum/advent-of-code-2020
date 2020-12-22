package io.github.tomplum.aoc.raft.food

/**
 * Maintains information about food-stuffs and their respective allergens.
 */
class FoodAllergies {
    private val data = mutableMapOf<String, String>()

    /**
     * Adds the given [allergen] and maps it to the given [food].
     * @param allergen The name of the allergen.
     * @param food The name of the food containing the allergen.
     */
    fun add(allergen: String, food: String) {
        data[allergen] = food
    }

    /**
     * Creates a canonical list of all the mapped allergenic foods.
     * @return The comma-separated list of food-stuffs.
     */
    fun getIngredientList(): String = data.toSortedMap().values.joinToString(",")
}