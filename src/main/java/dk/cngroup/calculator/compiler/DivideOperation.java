package dk.cngroup.calculator.compiler;

import dk.cngroup.calculator.annotation.BottomStack;
import dk.cngroup.calculator.annotation.IdentifyOperation;
import dk.cngroup.calculator.annotation.Operation;
import dk.cngroup.calculator.processor.DivideInstruction;
import dk.cngroup.calculator.processor.PlusInstruction;
import dk.cngroup.calculator.processor.StackInstruction;

import java.util.List;

@Operation
public class DivideOperation extends SimpleArithmeticOperation {

    private final String SYMBOL = "divide";

    @IdentifyOperation
    public boolean identify(OperationToken operationToken) {
        return SYMBOL.equals(operationToken.getOperation());
    }

    @BottomStack
    public List<StackInstruction> bottom(OperationToken operationToken) {
        return constructInstructionList(operationToken, new DivideInstruction());
    }

}
