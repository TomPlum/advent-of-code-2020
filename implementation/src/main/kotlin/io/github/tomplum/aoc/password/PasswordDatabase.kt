package io.github.tomplum.aoc.password

class PasswordDatabase {
    val passwords = mutableListOf<Pair<String, CorporatePolicy>>()

   /* fun validate(): List<String> = passwords.filter { (password, policy) ->

    }*/

    fun import(rawData: List<String>) = rawData.map { it.split(":") }.map { pair ->
        Pair(pair[1].trim(), CorporatePolicy(pair[0]))
    }.also { passwords.addAll(it) }
}