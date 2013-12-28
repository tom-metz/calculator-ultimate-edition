package dk.cngroup.calculator.compiler

import dk.cngroup.calculator.processor.StackInstruction
import spock.lang.Specification

class MultiplicationOperationTests extends Specification {

    private MultiplicationOperation multiplicationOperation
    private OperationToken multiplicationOperationToken

    void setup() {
        multiplicationOperation = new MultiplicationOperation()
        multiplicationOperationToken = new OperationToken('multiply 2')
    }

    void 'MULTIPLICATION operation is correctly identified'() {
        setup:
        OperationToken otherOperationToken = new OperationToken('other 9')

        when: 'method identify() with "MULTIPLICATION" token is called'
        boolean isMultiplicationOperation = multiplicationOperation.identify multiplicationOperationToken

        then: 'the MULTIPLICATION operation must be identified'
        isMultiplicationOperation

        when: 'method identify() with "other" token is called'
        isMultiplicationOperation = multiplicationOperation.identify otherOperationToken

        then: 'the MULTIPLICATION operation should NOT be identified'
        !isMultiplicationOperation
    }

    void 'MULTIPLICATION operation does not add any instructions at the TOP of the Stack'() {
        when: 'method top() is called'
        List<StackInstruction> instructions = multiplicationOperation.top multiplicationOperationToken

        then: 'empty collection is returned'
        0 == instructions.size()
    }

    void 'MULTIPLICATION operation adds instructions at the BOTTOM of the Stack'() {
        when: 'method bottom() is called'
        List<StackInstruction> instructions = multiplicationOperation.bottom multiplicationOperationToken

        then: 'collection size 1 is returned'
        1 == instructions.size()

        when: 'instruction is obtained and executed'
        Iterator<StackInstruction> iterator = instructions.iterator()
        StackInstruction stackInstruction = iterator.next()
        int result = stackInstruction.execute(5)

        then: 'result must be correctly calculated'
        10 == result
    }

}
