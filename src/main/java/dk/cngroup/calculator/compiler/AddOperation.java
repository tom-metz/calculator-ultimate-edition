package dk.cngroup.calculator.compiler;

import dk.cngroup.calculator.annotation.BottomStack;
import dk.cngroup.calculator.annotation.IdentifyOperation;
import dk.cngroup.calculator.annotation.Operation;
import dk.cngroup.calculator.annotation.TopStack;
import dk.cngroup.calculator.processor.PlusInstruction;
import dk.cngroup.calculator.processor.StackInstruction;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Operation
public class AddOperation extends SimpleArithmeticOperation {

    private final String SYMBOL = "add";

    @IdentifyOperation
    public boolean identify(OperationToken operationToken) {
        return SYMBOL.equals(operationToken.getOperation());
    }

    @BottomStack
    public List<StackInstruction> bottom(OperationToken operationToken) {
        return constructInstructionList(operationToken, new PlusInstruction());
    }

}
