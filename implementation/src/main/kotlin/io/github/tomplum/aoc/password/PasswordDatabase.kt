package io.github.tomplum.aoc.password

import io.github.tomplum.aoc.password.strategy.CorporatePolicy
import io.github.tomplum.aoc.password.strategy.SledRentalPolicy
import io.github.tomplum.aoc.password.strategy.TobogganPolicy

/**
 * The shopkeeper at the North Pole Toboggan Rental Shop is having a bad day.
 * - "Something's wrong with our computers; we can't log in!".
 * You ask if you can take a look.
 *
 * Maintains a list of passwords and can [validate] them against a given [policyType].
 */
class PasswordDatabase<T : CorporatePolicy>(private val policyType: Class<T>) {
    val passwords = mutableListOf<Pair<String, CorporatePolicy>>()

    /**
     * Validates all the [passwords] against the given [policyType].
     * @return The number of passwords that are valid.
     */
    fun validate(): Int = passwords.filter { (password, policy) -> policy.apply(password) }.count()

    /**
     * Imports a list of [rawData] exported from another [PasswordDatabase].
     * @throws IllegalArgumentException if the [policyType] is unknown.
     * @param rawData A list of rule-password data entries. E.g. 2-5 l: fllxf
     */
    fun import(rawData: List<String>) = rawData
        .map { entry -> entry.split(":") }
        .map { part -> Pair(part[0], part[1].trim()) }
        .map { (rule, password) ->
            val policy = when (policyType) {
                SledRentalPolicy::class.java -> SledRentalPolicy(rule)
                TobogganPolicy::class.java -> TobogganPolicy(rule)
                else -> throw IllegalArgumentException("Unknown Policy Type: ${policyType.simpleName}")
            }
            return@map Pair(password, policy)
        }.let { pair -> passwords.addAll(pair) }
}