package io.github.tomplum.aoc.password

data class CorporatePolicy(private val data: String) {

    val mandatoryCharacter: Char
    val minimumOccurrences: Int
    val maximumOccurrences: Int

    init {
        val policy = data.split(" ")
        mandatoryCharacter = policy[1].first()
        val occurrences = policy[0].split("-")
        minimumOccurrences = occurrences[0].toInt()
        maximumOccurrences = occurrences[1].toInt()
    }
}