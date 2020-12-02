package io.github.tomplum.aoc.password.strategy

/**
 * When the shopkeeper originally explained the details of the password policy, he
 * accidentally spoke of the one used at his old job at the sled rental place.
 *
 * The policy [data] describes both the minimum and maximum occurrences of the [mandatoryCharacter].
 * These are both inclusive meaning the password must contain 'at-least' and 'at-most' respectively.
 *
 * @param data a string representation of the policy parameters. e.g "1-3 a".
 * @see CorporatePolicy
 */
data class SledRentalPolicy(private val data: String) : CorporatePolicy {
    private val mandatoryCharacter: Char
    private val minimumOccurrences: Int
    private val maximumOccurrences: Int

    init {
        val policy = data.split(" ")
        mandatoryCharacter = policy[1].first()
        val occurrences = policy[0].split("-")
        minimumOccurrences = occurrences[0].toInt()
        maximumOccurrences = occurrences[1].toInt()
    }

    override fun apply(password: String): Boolean {
        val occurrences = password.filter { it == mandatoryCharacter }.length
        return occurrences in minimumOccurrences..maximumOccurrences
    }

    override fun toString(): String = data
}