package io.github.tomplum.aoc.password

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
    fun validate(): Int = passwords.filter { (password, policy) ->
        policy.apply(password)
    }.count()

    /**
     * Imports a list of [rawData] exported from another [PasswordDatabase].
     * @throws IllegalArgumentException if the [policyType] is unknown.
     * @see CorporatePolicy
     */
    fun import(rawData: List<String>) = rawData.map {
        it.split(":")
    }.map { pair ->
        val policy = when(policyType) {
            SledRentalPolicy::class.java -> SledRentalPolicy(pair[0])
            TobogganPolicy::class.java -> TobogganPolicy(pair[0])
            else -> throw IllegalArgumentException("Unknown Policy Type: ${policyType.javaClass.simpleName}")
        }
        Pair(pair[1].trim(), policy)
    }.also { passwords.addAll(it) }
}