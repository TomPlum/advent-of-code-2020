package io.github.tomplum.aoc.password

/**
 * The Official Toboggan Corporate Policy.
 *
 * The policy [data] describes two positions in a password, where 1 means the first character,
 * and 2 means the second character, and so on. This means this policy has no concept of zero-based
 * indexing.
 *
 * Exactly one of the aforementioned positions must contain the given [mandatoryCharacter].
 * Other occurrences of the letter are irrelevant for the purposes of the policy enforcement.
 *
 * @param data a string representation of the policy parameters. e.g "1-3 a".
 * @see CorporatePolicy
 */
data class TobogganPolicy(private val data: String) : CorporatePolicy {
    private val mandatoryCharacter: Char
    private val positionOne: Int
    private val positionTwo: Int

    init {
        val policy = data.split(" ")
        mandatoryCharacter = policy[1].first()
        val occurrences = policy[0].split("-")
        positionOne = occurrences[0].toInt() - 1
        positionTwo = occurrences[1].toInt() - 1
    }

    override fun apply(password: String): Boolean {
        return listOf(
            password.hasMandatoryCharAt(positionOne),
            password.hasMandatoryCharAt(positionTwo)
        ).filter { it }.count() == 1
    }

    override fun toString(): String = data

    private fun String.hasMandatoryCharAt(pos: Int) = this[pos] == mandatoryCharacter
}