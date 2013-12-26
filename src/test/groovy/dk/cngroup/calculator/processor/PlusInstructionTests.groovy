package dk.cngroup.calculator.processor

import spock.lang.Specification
import spock.lang.Unroll

class PlusInstructionTests extends Specification {

    private Instruction instruction;

    void setup() {
        instruction = new PlusInstruction()
    }

    @Unroll
    void 'Single operand computation of "plus" to #register an #operand results #result'(int register, int operand, int result) {
        expect:
        instruction.execute(register, operand) == result

        where:
        register | operand | result
         1 |  1 |  2
         0 |  1 |  1
        -2 |  1 | -1
        -2 | -3 | -5
         3 |  0 |  3
         0 |  0 |  0
        -1 |  4 |  3
    }

    @Unroll
    void 'Double operand computation of "plus" to #register an #operand1 and #operand2 results #result'(int register, int operand1, int operand2, int result) {
        expect:
        instruction.execute(register, operand1, operand2) == result

        where:
        register | operand1 | operand2 | result
         1 |  2 |  3 |  6
        -1 |  2 | -3 | -2
         0 |  0 |  0 |  0
    }

}
