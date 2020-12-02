package io.github.tomplum.aoc.password

/**
 * A policy that governs the validity of passwords in a [PasswordDatabase].
 */
interface CorporatePolicy {
    fun apply(password: String): Boolean
}