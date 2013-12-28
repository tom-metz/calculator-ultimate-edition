package dk.cngroup.calculator.compiler

import dk.cngroup.calculator.processor.StackInstruction
import spock.lang.Specification

class RemoveOperationTests extends Specification {

    private RemoveOperation removeOperation
    private OperationToken removeOperationToken

    void setup() {
        removeOperation = new RemoveOperation()
        removeOperationToken = new OperationToken('remove 3')
    }

    void 'REMOVE operation is correctly identified'() {
        setup:
        OperationToken otherOperationToken = new OperationToken('other 9')

        when: 'method identify() with "remove" token is called'
        boolean isRemoveOperation = removeOperation.identify removeOperationToken

        then: 'the REMOVE operation must be identified'
        isRemoveOperation

        when: 'method identify() with "other" token is called'
        isRemoveOperation = removeOperation.identify otherOperationToken

        then: 'the REMOVE operation should NOT be identified'
        !isRemoveOperation
    }

    void 'REMOVE operation does not add any instructions at the TOP of the Stack'() {
        when: 'method top() is called'
        List<StackInstruction> instructions = removeOperation.top removeOperationToken

        then: 'empty collection is returned'
        0 == instructions.size()
    }

    void 'REMOVE operation adds instructions at the BOTTOM of the Stack'() {
        when: 'method bottom() is called'
        List<StackInstruction> instructions = removeOperation.bottom removeOperationToken

        then: 'collection size 1 is returned'
        1 == instructions.size()

        when: 'instruction is obtained and executed'
        Iterator<StackInstruction> iterator = instructions.iterator()
        StackInstruction stackInstruction = iterator.next()
        int result = stackInstruction.execute(7)

        then: 'result must be correctly calculated'
        4 == result
    }

}
