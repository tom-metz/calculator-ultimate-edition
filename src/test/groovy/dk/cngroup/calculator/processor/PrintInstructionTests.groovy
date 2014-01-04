package dk.cngroup.calculator.processor

import spock.lang.Specification
import spock.lang.Unroll

class PrintInstructionTests extends Specification {

    private Instruction instruction

    void setup() {
        instruction = new PrintInstruction()
    }

    @Unroll
    void 'Single operand computation of "print" to #register an #operand results #result'(int register, int operand, int result) {
        expect:
        instruction.execute(register, operand) == result

        where:
        register | operand | result
         1 |  1 |  1
         0 |  1 |  0
        -2 |  1 | -2
        -2 | -3 | -2
         3 |  0 |  3
         0 |  0 |  0
        -1 |  4 | -1
    }

    @Unroll
    void 'Double operand computation of "print" to #register an #operand1 and #operand2 results #result'(int register, int operand1, int operand2, int result) {
        expect:
        instruction.execute(register, operand1, operand2) == result

        where:
        register | operand1 | operand2 | result
         1 |  2 |  3 |  1
        -1 |  2 | -3 | -1
         0 |  0 |  0 |  0
    }

}
