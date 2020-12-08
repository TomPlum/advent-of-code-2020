package io.github.tomplum.aoc.bootcode

import io.github.tomplum.aoc.bootcode.Operation.JUMP
import io.github.tomplum.aoc.bootcode.Operation.NO_OPERATION

/**
 * A utility class for repairing corrupt [Program].
 */
class ProgramRepairAgent {
    /**
     * Analysis of the [Program] shows that exactly one [Instruction] is corrupted.
     * Somewhere in the program, either a [JUMP] is supposed to be a [NO_OPERATION], or vice-versa.
     * This function generates a list of all possible instruction permutations from the given [corruptedProgram] by
     * replacing the aforementioned operations. These programs candidates are then emulated to verify their correctness.
     *
     * @return The repaired program. It will have one [JUMP] or [NO_OPERATION] replaced.
     */
    fun fix(corruptedProgram: Program): Program {
        if (corruptedProgram.isValid()) throw IllegalArgumentException("Program is valid and cannot be repaired.")
        val instructions = corruptedProgram.instructions
        val programCandidates = instructions.mapIndexedNotNull { i, inst ->
            when(inst.code) {
                JUMP -> instructions.toMutableList().apply { set(i, Instruction(NO_OPERATION, inst.argument)) }
                NO_OPERATION -> instructions.toMutableList().apply { set(i, Instruction(JUMP, inst.argument)) }
                else -> null
            }
        }.map { Program(it) }

        return programCandidates.first { program -> program.isValid() }
    }

    private fun Program.isValid() = try { Runtime(this).run(); true } catch (e: CorruptProgram) { false }
}