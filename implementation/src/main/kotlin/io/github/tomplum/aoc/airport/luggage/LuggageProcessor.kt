package io.github.tomplum.aoc.airport.luggage

/**
 * Due to recent aviation regulations, many rules are being enforced about bags and their contents; bags must be
 * color-coded and must contain specific quantities of other color-coded bags.
 * Apparently, nobody responsible for these regulations considered how long they would take to enforce!
 *
 * Each of the luggage bags that are processed can contain other bags (and so-on in a recursive nature).
 * @see LuggageBag
 *
 * @param luggage A set of root nodes containing sub-trees of all the luggage to be processed
 */
class LuggageProcessor(private val luggage: Set<LuggageBag>) {

    /**
     * Finds the luggage bag colours that can eventually contains at least one bag of the given [colour].
     * @param colour The target colour to search for.
     * @return The quantity of bag colours found.
     */
    fun getBagColoursContaining(colour: String): Int = findNode(colour).getEnclosingBags().size

    /**
     * Finds how many individual luggage bags are required inside a single bad of the given [colour].
     * @param colour The target colour to search for.
     * @return The total quantity of all the required bags.
     */
    fun getBagRequirement(colour: String): Int = findNode(colour).getBagRequirement() - 1

    /**
     * Finds the node in the sub-trees of the given [luggage] root nodes that matches the given [colour].
     * @param colour The target colour to search for.
     * @throws NoSuchElementException if a luggage bag with the given [colour] cannot be found.
     */
    private fun findNode(colour: String): LuggageBag = luggage.mapNotNull { bag -> bag.getNestedBag(colour) }.first()
}