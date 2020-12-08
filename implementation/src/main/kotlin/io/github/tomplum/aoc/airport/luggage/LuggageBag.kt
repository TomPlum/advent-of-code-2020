package io.github.tomplum.aoc.airport.luggage

/**
 * A single luggage bag in a tree of other recursively nested bags.
 *
 * @see LuggageProcessor
 * @see LuggageRuleParser
 *
 * @property parents The bags in which this bag can be contained in.
 * @property children The bags which can be contained in this bag and their respective quantity requirements.
 */
data class LuggageBag(private val colour: String) {
    private val parents: MutableList<LuggageBag> = mutableListOf()
    private val children: MutableMap<LuggageBag, Int> = mutableMapOf()

    /**
     * Adds a single child [bag] with the given [quantity] requirement.
     */
    fun addChild(bag: LuggageBag, quantity: Int) {
        children[bag] = quantity
        bag.parents.add(this)
    }

    /**
     * Gets a single node from the tree with the given [colour].
     * @return The found node or null if no such node exists.
     */
    fun getNode(colour: String): LuggageBag? {
        if (this.colour == colour) return this
        return children.keys.map { it.getNode(colour) }.firstOrNull()
    }

    //fun getAncestorCount(): Int = parents.size + parents.sumBy { it.getAncestorCount() }

    fun getAncestors(): Set<LuggageBag> = parents.toSet() + parents.flatMap { it.getAncestors() }

    /**
     * Calculates the total number of bags required for one of this bag.
     * Each bag maintains a map of [children] which outlines how many of other bags are required.
     * This function recursively sums the products of these values to produce the overall requirement.
     * @return The total number of bags required (plus 1) - for some reason?
     */
    fun getBagRequirement(): Int = children.map { (bag, quantity) -> bag.getBagRequirement() * quantity }.sum() + 1

    /**
     * Checks if this luggage bag is a root node in the tree.
     */
    fun isRoot(): Boolean = parents.size == 0
}