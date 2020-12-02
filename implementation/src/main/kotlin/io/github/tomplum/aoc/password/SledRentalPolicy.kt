package io.github.tomplum.aoc.password

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