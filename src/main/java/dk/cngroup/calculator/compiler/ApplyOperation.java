package dk.cngroup.calculator.compiler;

import dk.cngroup.calculator.annotation.BottomStack;
import dk.cngroup.calculator.annotation.IdentifyOperation;
import dk.cngroup.calculator.annotation.Operation;
import dk.cngroup.calculator.annotation.TopStack;
import dk.cngroup.calculator.processor.SetInstruction;
import dk.cngroup.calculator.processor.StackInstruction;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Operation(execute = true)
public class ApplyOperation {

    private final String SYMBOL = "apply";

    @IdentifyOperation
    public boolean identify(OperationToken operationToken) {
        return SYMBOL.equals(operationToken.getOperation());
    }

    @TopStack
    public List<StackInstruction> top(OperationToken operationToken) {
        List<StackInstruction> topStack = new ArrayList<StackInstruction>(1);
        StackInstruction stackInstruction = new StackInstruction(new SetInstruction(), operationToken.getOperands());
        topStack.add(stackInstruction);
        return topStack;
    }

    @BottomStack
    public List<StackInstruction> bottom(OperationToken operationToken) {
        return Collections.<StackInstruction>emptyList();
    }

}
