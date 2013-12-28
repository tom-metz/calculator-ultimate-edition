package dk.cngroup.calculator.compiler;

import dk.cngroup.calculator.annotation.TopStack;
import dk.cngroup.calculator.processor.Instruction;
import dk.cngroup.calculator.processor.StackInstruction;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SimpleArithmeticOperation {

    @TopStack
    public List<StackInstruction> top(OperationToken operationToken) {
        return Collections.<StackInstruction>emptyList();
    }

    List<StackInstruction> constructInstructionList(OperationToken operationToken, Instruction instruction) {
        List<StackInstruction> instructions = new ArrayList<StackInstruction>(1);
        StackInstruction stackInstruction = new StackInstruction(instruction, operationToken.getOperands());
        instructions.add(stackInstruction);
        return instructions;
    }

}
