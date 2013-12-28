package dk.cngroup.calculator.processor

import spock.lang.Specification

class StackInstructionTests extends Specification {

    void 'StackInstruction should execute given instruction and operand'() {
        setup:
        Instruction instruction = new MultiplicationInstruction()
        int[] operands = [3]
        StackInstruction stackInstruction = new StackInstruction(instruction, operands)

        when: 'StackInstruction execute() is called'
        int result = stackInstruction.execute 4

        then: 'result must be equal to expectation'
        12 == result
    }

}
