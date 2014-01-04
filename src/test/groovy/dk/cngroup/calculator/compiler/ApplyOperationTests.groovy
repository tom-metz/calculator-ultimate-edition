package dk.cngroup.calculator.compiler

import dk.cngroup.calculator.processor.StackInstruction
import spock.lang.Specification

class ApplyOperationTests extends Specification {

    private ApplyOperation applyOperation
    private OperationToken applyOperationToken

    void setup() {
        applyOperation = new ApplyOperation()
        applyOperationToken = new OperationToken('apply 5')
    }

    void 'APPLY operation is correctly identified'() {
        setup:
        OperationToken otherOperationToken = new OperationToken('other 9')

        when: 'method identify() with "apply" token is called'
        boolean isApplyOperation = applyOperation.identify applyOperationToken

        then: 'the APPLY operation must be identified'
        isApplyOperation

        when: 'method identify() with "other" token is called'
        isApplyOperation = applyOperation.identify otherOperationToken

        then: 'the APPLY operation should NOT be identified'
        !isApplyOperation
    }

    void 'APPLY operation adds instructions at the TOP of the Stack'() {
        when: 'method top() is called'
        List<StackInstruction> instructions = applyOperation.top applyOperationToken

        then: 'empty collection is returned'
        1 == instructions.size()

        when: 'instruction is obtained and executed'
        Iterator<StackInstruction> iterator = instructions.iterator()
        StackInstruction stackInstruction = iterator.next()
        int result = stackInstruction.execute(3)

        then: 'result must be correctly calculated'
        5 == result // value 5 is from setup(), which must be used
    }

    void 'APPLY operation adds instructions at the BOTTOM of the Stack'() {
        when: 'method bottom() is called'
        List<StackInstruction> instructions = applyOperation.bottom applyOperationToken

        then: 'empty collection is returned'
        0 == instructions.size()
    }

}
