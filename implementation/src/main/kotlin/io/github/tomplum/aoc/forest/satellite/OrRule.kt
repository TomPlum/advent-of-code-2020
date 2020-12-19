package io.github.tomplum.aoc.forest.satellite

/**
 * A rule governing that a given [Message] should match all in the IDs in at-least one of the [ids] sub-lists.
 * @see MessageReport
 * @param ids A list of ID lists of which one much be completed matched.
 */
data class OrRule(val ids: List<List<Int>>): MessageRule