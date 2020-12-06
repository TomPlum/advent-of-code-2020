package io.github.tomplum.aoc.airport.customs

/**
 * Passengers must complete a 'Customs Declaration Form' before take-off, consisting of 26 yes-or-no questions.
 * @param data A collection of answers from every group on the plane.
 */
class Questionnaire(private val data: String) {
    /**
     * Counts the number of questions to which anyone passenger answered "yes" in each group.
     * @return The sum of the "yes" answers from each group.
     */
    fun getYesQuestionCount(): Int = data.split("\n\n")
        .map { it.replace("\n", "") }
        .map { it.toList().distinct() }
        .sumBy { it.size }

    /**
     * Counts the number of questions to which every passenger answered "yes" in each group.
     * @return The sum of the "yes" answers from each group.
     */
    fun getCommonYesQuestionCount(): Int = data
        .split("\n\n")
        .map { it.split("\n") }
        .map { group -> group.map { line -> line.toCharArray().toList() } }
        .map { group -> group.reduce { acc, set -> acc.intersect(set).toList() } }
        .sumBy { it.size }
}