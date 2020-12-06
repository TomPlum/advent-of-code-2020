package io.github.tomplum.aoc.airport.customs

class Questionnaire(private val data: String) {
    fun getCorrectQuestionCount(): Int = data.split("\n\n")
        .map { it.replace("\n", "") }
        .map { it.toList().distinct() }
        .sumBy { it.size }

    fun getCommonCorrectQuestionCount(): Int = data
        .split("\n\n")
        .map { it.split("\n") }
        .map { group -> group.map { line -> line.toCharArray().toList() } }
        .map { group -> group.reduce { acc, set -> acc.intersect(set).toList() } }
        .sumBy { it.size }
}