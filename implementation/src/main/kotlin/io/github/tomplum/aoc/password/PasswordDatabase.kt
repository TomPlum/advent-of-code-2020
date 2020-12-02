package io.github.tomplum.aoc.password

class PasswordDatabase {
    val passwords = mutableListOf<Pair<String, CorporatePolicy>>()

    fun validate(): Int = passwords.filter { (password, policy) ->
        val occurrences = password.filter { it == policy.mandatoryCharacter }.length
        occurrences >= policy.minimumOccurrences && occurrences <= policy.maximumOccurrences
    }.count()

    fun import(rawData: List<String>) = rawData.map { it.split(":") }.map { pair ->
        Pair(pair[1].trim(), CorporatePolicy(pair[0]))
    }.also { passwords.addAll(it) }
}