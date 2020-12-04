package io.github.tomplum.aoc.passport

class PassportReader private constructor() {
    companion object {
        fun read(data: List<String>): List<Passport> {
            return data.joinToString(" ") { if (it.isEmpty()) "~~" else it }
                .split("~~").asSequence().map { it.trim() }.map { it.split(" ") }
                .map { pairData -> pairData.map { it.split(":") } }
                .map { pairs -> pairs.map { Pair(PassportField.fromString(it[0]), it[1]) } }
                .map { fields -> fields.toMap()}
                .map { Passport(it) }.toList()
        }
    }
}