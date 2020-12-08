package io.github.tomplum.aoc.bootcode

import java.lang.reflect.Array.set

class BootCodeRepairAgent {
    private var acc = 0
    private var programIndex = 0
    private val executed = mutableSetOf<Int>()

    fun fix(corruptedProgram: BootCodeProgram): BootCodeProgram {
        var offendingInstruction: BootCodeInstruction? = null

        /*while (offendingInstruction == null) {
            val instruction = corruptedProgram.instructions[programIndex]
            if (executed.contains(programIndex)) {
                offendingInstruction = corruptedProgram.instructions[executed.last()]
                break
            }
            executed.add(programIndex)
            when (instruction.code) {
                BootCode.ACCUMULATE -> {
                    acc += instruction.argument
                    programIndex++
                }
                BootCode.JUMP -> programIndex += instruction.argument
                BootCode.NO_OPERATION -> programIndex++
            }
        }

        val instructions = corruptedProgram.instructions.toMutableList()
        if (offendingInstruction!!.code == BootCode.JUMP) {
            instructions[programIndex] = BootCodeInstruction(BootCode.NO_OPERATION, 0)
        }
        if (offendingInstruction.code == BootCode.NO_OPERATION) {
            instructions[programIndex] = BootCodeInstruction(BootCode.JUMP, 3)
        }*/
        val instructions = corruptedProgram.instructions
        val programCandidates = instructions.foldIndexed(mutableListOf<List<BootCodeInstruction>>()) { i, acc, inst ->
            val candidate = when(inst.code) {
                BootCode.JUMP -> {
                    instructions.toMutableList().apply {
                        set(i, BootCodeInstruction(BootCode.NO_OPERATION, inst.argument))
                    }
                }
                BootCode.NO_OPERATION -> {
                    instructions.toMutableList().apply {
                        set(i, BootCodeInstruction(BootCode.JUMP, inst.argument))
                    }
                }
                else -> mutableListOf()
            }
            acc.add(candidate)
            acc
        }.filterNot { it.isEmpty() }.map { BootCodeProgram(it) }

        val outputs = programCandidates.filter { program ->
            val runtime = BootCodeRuntime(program)
            try {
                runtime.run()
                true
            } catch (e: InfiniteLoopFound) {
                false
            }
        }
        return outputs.first()
    }
}