package io.github.tomplum.aoc.airport.customs

class Questionnaire(private val data: String) {
    fun getCorrectQuestionCount(): Int = data.split("\n\n")
        .map { it.replace("\n", "") }
        .map { it.toList().distinct() }
        .sumBy { it.size }
}