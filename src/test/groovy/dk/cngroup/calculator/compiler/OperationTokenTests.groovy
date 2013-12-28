package dk.cngroup.calculator.compiler

import spock.lang.Specification
import spock.lang.Unroll

class OperationTokenTests extends Specification {

    @Unroll
    void "Correct instance creation from '#raw' identifies '#operation' and '#operandsSize' operand '#operand'"(String raw, String operation, int operandsSize, String operand) {
        given:
        OperationToken operationToken = new OperationToken(raw)
        operation == operationToken.getOperation()
        operandsSize == operationToken.getOperands().length
        if (operandsSize > 0) {
            operand == operationToken.getOperands()[0]
        }

        where:
        raw | operation | operandsSize | operand
        'add 5'    |   'add' | 1 |    5
        'ADD 4'    |   'add' | 1 |    4
        'minus -3' | 'minus' | 1 |   -3
        'add'      |   'add' | 0 | null
        ''         |    null | 0 | null
        ' '        |    null | 0 | null
        'add    7' |   'add' | 1 |    7
    }

    @Unroll
    void "Correct instance creation from '#raw' identifies '#operation' with '#operandsSize' operands '#operand1' and '#operand2'"(String raw, String operation, int operandsSize, String operand1, String operand2) {
        given:
        OperationToken operationToken = new OperationToken(raw)
        operation == operationToken.getOperation()
        operandsSize == operationToken.getOperands().length
        if (operandsSize > 0) {
            operand1 == operationToken.getOperands()[0]
            operand2 == operationToken.getOperands()[1]
        }

        where:
        raw | operation | operandsSize | operand1 | operand2
        'add 1 2'    |   'add' | 2 |  1 |  2
        'ADD 4 -3'   |   'add' | 2 |  4 | -3
        'minus -3 0' | 'minus' | 2 | -3 |  0
        'add  3   1' |   'add' | 2 |  3 |  1
    }

}
