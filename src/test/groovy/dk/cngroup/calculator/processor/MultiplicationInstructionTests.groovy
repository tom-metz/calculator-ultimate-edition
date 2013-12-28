package dk.cngroup.calculator.processor

import spock.lang.Specification
import spock.lang.Unroll

class MultiplicationInstructionTests extends Specification {

    private Instruction instruction

    void setup() {
        instruction = new MultiplicationInstruction()
    }

    @Unroll
    void 'Single operand computation of "multiple" to #register an #operand results #result'(int register, int operand, int result) {
        expect:
        instruction.execute(register, operand) == result

        where:
        register | operand | result
         1 |  1 |  1
         0 |  1 |  0
        -2 |  1 | -2
        -2 | -3 |  6
         3 |  2 |  6
         0 |  0 |  0
        -1 |  4 | -4
    }

    @Unroll
    void 'Double operand computation of "multiple" to #register an #operand1 and #operand2 results #result'(int register, int operand1, int operand2, int result) {
        expect:
        instruction.execute(register, operand1, operand2) == result

        where:
        register | operand1 | operand2 | result
         1 |  2 |  3 |  6
        -1 |  2 | -3 |  6
         0 |  0 |  0 |  0
    }

}
