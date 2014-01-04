package dk.cngroup.calculator.compiler

import dk.cngroup.calculator.processor.StackInstruction
import spock.lang.Specification

class OperationSuiteTests extends Specification {

    void setup() {
    }

    void cleanup() {
    }

    void 'Operation is correctly obtained via reflection from target package'() {
        setup:
        OperationSuite operationSuite = new OperationSuite('dk.cngroup.calculator.test')
        OperationToken operationToken = new OperationToken('test 7')

        when: 'method identify() is called'
        OperationProxy operationProxy = operationSuite.identifyOperation(operationToken)
        List<StackInstruction> topStackInstructions = operationProxy.invokeTopStack(operationToken)
        List<StackInstruction> bottomStackInstructions = operationProxy.invokeBottomStack(operationToken)
        StackInstruction topStackInstruction = topStackInstructions.iterator().next()
        StackInstruction bottomStackInstruction = bottomStackInstructions.iterator().next()

        then: 'expected instructions defined by the operation are included in the suite'
        !operationProxy.isExecutable()
        8 == topStackInstruction.execute(1)
        1 == bottomStackInstruction.execute(8)
    }

}
