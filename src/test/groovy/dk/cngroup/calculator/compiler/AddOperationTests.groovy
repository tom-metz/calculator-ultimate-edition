package dk.cngroup.calculator.compiler

import dk.cngroup.calculator.processor.StackInstruction
import spock.lang.Specification

class AddOperationTests extends Specification {

    private AddOperation addOperation
    private OperationToken addOperationToken

    void setup() {
        addOperation = new AddOperation()
        addOperationToken = new OperationToken('add 5')
    }

    void 'ADD operation is correctly identified'() {
        setup:
        OperationToken otherOperationToken = new OperationToken('other 9')

        when: 'method identify() with "add" token is called'
        boolean isAddOperation = addOperation.identify addOperationToken

        then: 'the ADD operation must be identified'
        isAddOperation

        when: 'method identify() with "other" token is called'
        isAddOperation = addOperation.identify otherOperationToken

        then: 'the ADD operation should NOT be identified'
        !isAddOperation
    }

    void 'ADD operation does not add any instructions at the TOP of the Stack'() {
        when: 'method top() is called'
        List<StackInstruction> instructions = addOperation.top addOperationToken

        then: 'empty collection is returned'
        0 == instructions.size()
    }

    void 'ADD operation adds instructions at the BOTTOM of the Stack'() {
        when: 'method bottom() is called'
        List<StackInstruction> instructions = addOperation.bottom addOperationToken

        then: 'collection size 1 is returned'
        1 == instructions.size()

        when: 'instruction is obtained and executed'
        Iterator<StackInstruction> iterator = instructions.iterator()
        StackInstruction stackInstruction = iterator.next()
        int result = stackInstruction.execute(3)

        then: 'result must be correctly calculated'
        8 == result
    }

}
