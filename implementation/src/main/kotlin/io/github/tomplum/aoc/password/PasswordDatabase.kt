package io.github.tomplum.aoc.password

class PasswordDatabase {
    val passwords = mutableListOf<Pair<String, CorporatePolicy>>()

    fun validate(): Int = passwords.filter { (password, policy) ->
        policy.apply(password)
    }.count()

    fun <T : CorporatePolicy> import(rawData: List<String>, policyType: Class<T>) = rawData.map {
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