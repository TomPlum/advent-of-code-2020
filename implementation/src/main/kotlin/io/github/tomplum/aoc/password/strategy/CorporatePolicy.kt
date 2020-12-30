package io.github.tomplum.aoc.password.strategy

import io.github.tomplum.aoc.password.PasswordDatabase

/**
 * A policy that governs the validity of passwords in a [PasswordDatabase].
 */
interface CorporatePolicy {
    fun apply(password: String): Boolean
}