package io.github.tomplum.aoc.airport.passport

/**
 * Reads passport data and produces a list of [Passport]s.
 */
class PassportReader private constructor() {
    companion object {
        fun read(data: String): List<Passport> {
            return data.split("\n\n")
                .map { it.split("\n").joinToString(" ") }
                .asSequence()
                .map { it.split(" ") }
                .map { pairData -> pairData.map { it.split(":") } }
                .map { pairs -> pairs.map { Pair(PassportField.fromString(it[0]), it[1]) } }
                .map { fields -> fields.toMap()}
                .map { Passport(it) }.toList()
        }
    }
}