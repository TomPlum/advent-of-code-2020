package io.github.tomplum.aoc.airport.luggage

/**
 * Parses the aviation regulations rules and produces a tree of [LuggageBag]s.
 * @see LuggageProcessor
 */
class LuggageRuleParser private constructor() {
    companion object {
        fun parse(rules: List<String>): Set<LuggageBag> {
            val parsedRules = rules.map { it.replace(" bags", "").replace(" bag", "") }

            val uniqueNodes = parsedRules.flatMap { rule ->
                val info = rule.split(" contain ")
                val parentColour = info[0]
                val data = info[1].dropLast(1).split(", ").filter { it != "no other" }
                val parent = LuggageBag(parentColour)
                val children = data.map { datum ->
                    LuggageBag(datum.substring(2))
                }
                children + parent
            }.distinct()

            parsedRules.forEach { rule ->
                val info = rule.split(" contain ")
                val parentColour = info[0]
                val data = info[1].dropLast(1).split(", ").filter { it != "no other" }
                val parentNode = uniqueNodes.find { it == LuggageBag(parentColour) }!!

                data.fold(parentNode) { parent, datum ->
                    parent.apply {
                        val childColour = datum.substring(2)
                        val childQuantity = datum.take(1).toInt()
                        val child = uniqueNodes.find { it == LuggageBag(childColour) }!!
                        addChild(child, childQuantity)
                    }
                }
            }

            return uniqueNodes.filter { it.isRoot() }.toSet()
        }
    }
}