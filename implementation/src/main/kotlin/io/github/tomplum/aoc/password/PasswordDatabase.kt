package io.github.tomplum.aoc.password

class PasswordDatabase {
    val passwords = mutableListOf<Pair<String, CorporatePolicy>>()

    fun validate(): Int = passwords.filter { (password, policy) -> policy.apply(password) }.count()

    fun import(rawData: List<String>) = rawData.map { it.split(":") }.map { pair ->
        Pair(pair[1].trim(), SledRentalPolicy(pair[0]))
    }.also { passwords.addAll(it) }
}