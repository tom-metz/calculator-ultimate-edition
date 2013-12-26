package dk.cngroup.calculator.processor

import spock.lang.Specification
import spock.lang.Unroll

class DivideInstructionTests extends Specification {

    private Instruction instruction;

    void setup() {
        instruction = new DivideInstruction()
    }

    @Unroll
    void 'Single operand computation of "divide" to #register an #operand results #result'(int register, int operand, int result) {
        expect:
        instruction.execute(register, operand) == result

        where:
        register | operand | result
         1 |  1 |  1
         0 |  1 |  0
        -2 |  1 | -2
        -4 | -2 |  2
         6 |  2 |  3
         9 | -3 | -3
         0 |  4 |  0
    }

    @Unroll
    void 'Double operand computation of "divide" to #register an #operand1 and #operand2 results #result'(int register, int operand1, int operand2, int result) {
        expect:
        instruction.execute(register, operand1, operand2) == result

        where:
        register | operand1 | operand2 | result
         8 |  2 |  2 |  2
        -8 |  2 |  2 | -2
         8 | -2 | -2 |  2
    }

    void 'Division by zero should throw exception'() {
        when: 'Operand iz zero'
        instruction.execute(4, 0);

        then: 'exception is throws'
        thrown(IncorrectInstructionException)
    }

}
