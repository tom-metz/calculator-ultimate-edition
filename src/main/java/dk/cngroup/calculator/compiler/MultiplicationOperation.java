package dk.cngroup.calculator.compiler;

import dk.cngroup.calculator.annotation.BottomStack;
import dk.cngroup.calculator.annotation.IdentifyOperation;
import dk.cngroup.calculator.annotation.Operation;
import dk.cngroup.calculator.processor.MinusInstruction;
import dk.cngroup.calculator.processor.MultiplicationInstruction;
import dk.cngroup.calculator.processor.StackInstruction;

import java.util.List;

@Operation
public class MultiplicationOperation extends SimpleArithmeticOperation {

    private final String SYMBOL = "multiply";

    @IdentifyOperation
    public boolean identify(OperationToken operationToken) {
        return SYMBOL.equals(operationToken.getOperation());
    }

    @BottomStack
    public List<StackInstruction> bottom(OperationToken operationToken) {
        return constructInstructionList(operationToken, new MultiplicationInstruction());
    }

}
