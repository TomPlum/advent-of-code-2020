package io.github.tomplum.aoc.password

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