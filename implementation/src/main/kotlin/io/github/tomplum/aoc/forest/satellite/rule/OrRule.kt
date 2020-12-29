package io.github.tomplum.aoc.forest.satellite.rule

import io.github.tomplum.aoc.forest.satellite.Message
import io.github.tomplum.aoc.forest.satellite.MessageReport

/**
 * A rule governing that a given [Message] should match all in the IDs in at-least one of the [ids] sub-lists.
 * @see MessageReport
 * @param ids A list of ID lists of which one much be completed matched.
 */
data class OrRule(val ids: List<List<Int>>): MessageRule