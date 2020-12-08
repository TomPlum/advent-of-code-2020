package io.github.tomplum.aoc.bootcode

class BootCodeRuntime(private val program: BootCodeProgram) {
    private var acc = 0
    private var programIndex = 0
    private val executed = mutableSetOf<Int>()

    fun runOnce(): Int = try { run() } catch (e: CorruptBootCodeProgram) { acc }

    fun run(): Int {
        while (programIndex <= program.instructions.indices.last) {
            if (executed.contains(programIndex)) {
                throw CorruptBootCodeProgram()
            }
            val instruction = program.instructions[programIndex]
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
        return acc
    }
}