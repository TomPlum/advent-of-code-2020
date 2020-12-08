package io.github.tomplum.aoc.bootcode

/**
 * Executes the given [program]
 */
class Runtime(private val program: Program) {

    private var accumulator = 0

    /**
     * Runs the [program] and returns the value in the [accumulator] when it terminates.
     * @throws CorruptProgram if the program begins to execute the same [Instruction] for a second time.
     * @return The value in the [accumulator] after the [program] finishes executing successfully.
     */
    fun run(): Int {
        val executed = mutableSetOf<Int>()
        var programIndex = 0

        while (programIndex <= program.instructions.indices.last) {
            if (executed.contains(programIndex)) {
                throw CorruptProgram()
            }
            val instruction = program.instructions[programIndex]
            executed.add(programIndex)
            when (instruction.code) {
                Operation.ACCUMULATE -> {
                    accumulator += instruction.argument
                    programIndex++
                }
                Operation.JUMP -> programIndex += instruction.argument
                Operation.NO_OPERATION -> programIndex++
            }
        }
        return accumulator
    }

    /**
     * Runs the [program] and returns the value in the [accumulator] when it terminates.
     *
     * If the program is corrupt, then the value returned will be the state of the [accumulator] before the program
     * begins the second iteration of its infinite loop. Hence it runs once.
     * @see ProgramRepairAgent
     *
     * If the program is valid, then it will simply run to completion and return the [accumulator] value once finished.
     * @see run
     *
     * @return The value in the [accumulator] when the [program] finishes executing or throws [CorruptProgram].
     */
    fun runOnce(): Int = try { run() } catch (e: CorruptProgram) { accumulator }
}