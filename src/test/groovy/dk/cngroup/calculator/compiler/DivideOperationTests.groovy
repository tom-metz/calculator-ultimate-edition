package dk.cngroup.calculator.compiler

import dk.cngroup.calculator.processor.StackInstruction
import spock.lang.Specification

class DivideOperationTests extends Specification {

    private DivideOperation divideOperation
    private OperationToken divideOperationToken

    void setup() {
        divideOperation = new DivideOperation()
        divideOperationToken = new OperationToken('divide 2')
    }

    void 'DIVIDE operation is correctly identified'() {
        setup:
        OperationToken otherOperationToken = new OperationToken('other 9')

        when: 'method identify() with "DIVIDE" token is called'
        boolean isDivideOperation = divideOperation.identify divideOperationToken

        then: 'the DIVIDE operation must be identified'
        isDivideOperation

        when: 'method identify() with "other" token is called'
        isDivideOperation = divideOperation.identify otherOperationToken

        then: 'the DIVIDE operation should NOT be identified'
        !isDivideOperation
    }

    void 'DIVIDE operation does not DIVIDE any instructions at the TOP of the Stack'() {
        when: 'method top() is called'
        List<StackInstruction> instructions = divideOperation.top divideOperationToken

        then: 'empty collection is returned'
        0 == instructions.size()
    }

    void 'DIVIDE operation adds instructions at the BOTTOM of the Stack'() {
        when: 'method bottom() is called'
        List<StackInstruction> instructions = divideOperation.bottom divideOperationToken

        then: 'collection size 1 is returned'
        1 == instructions.size()

        when: 'instruction is obtained and executed'
        Iterator<StackInstruction> iterator = instructions.iterator()
        StackInstruction stackInstruction = iterator.next()
        int result = stackInstruction.execute(12)

        then: 'result must be correctly calculated'
        6 == result
    }

}
