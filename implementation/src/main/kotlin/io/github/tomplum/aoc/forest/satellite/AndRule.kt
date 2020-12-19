package io.github.tomplum.aoc.forest.satellite

/**
 * A rule governing that a given [Message] must match all the rules with the given [ids].
 * @see MessageReport
 * @param ids A list of IDs, all of which must be matched.
 */
data class AndRule(val ids: List<Int>): MessageRule