package io.github.tomplum.aoc.bootcode

import io.github.tomplum.aoc.bootcode.BootCode.JUMP
import io.github.tomplum.aoc.bootcode.BootCode.NO_OPERATION

class BootCodeRepairAgent {
    fun fix(corruptedProgram: BootCodeProgram): BootCodeProgram {
        val instructions = corruptedProgram.instructions
        val programCandidates = instructions.mapIndexedNotNull { i, inst ->
            when(inst.code) {
                JUMP -> instructions.toMutableList().apply { set(i, BootCodeInstruction(NO_OPERATION, inst.argument)) }
                NO_OPERATION -> instructions.toMutableList().apply { set(i, BootCodeInstruction(JUMP, inst.argument)) }
                else -> null
            }
        }.map { BootCodeProgram(it) }

        val outputs = programCandidates.filter { program ->
            val runtime = BootCodeRuntime(program)
            try {
                runtime.run()
                true
            } catch (e: CorruptBootCodeProgram) {
                false
            }
        }
        return outputs.first()
    }
}