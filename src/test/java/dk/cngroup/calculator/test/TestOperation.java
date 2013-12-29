package dk.cngroup.calculator.test;

import dk.cngroup.calculator.annotation.BottomStack;
import dk.cngroup.calculator.annotation.IdentifyOperation;
import dk.cngroup.calculator.annotation.Operation;
import dk.cngroup.calculator.annotation.TopStack;
import dk.cngroup.calculator.compiler.OperationToken;
import dk.cngroup.calculator.processor.Instruction;
import dk.cngroup.calculator.processor.MinusInstruction;
import dk.cngroup.calculator.processor.PlusInstruction;
import dk.cngroup.calculator.processor.StackInstruction;

import java.util.ArrayList;
import java.util.List;

@Operation
public class TestOperation {

    private final String SYMBOL = "test";

    @IdentifyOperation
    public boolean identify(OperationToken operationToken) {
        System.out.println("Test identify() called");
        return SYMBOL.equals(operationToken.getOperation());
    }

    @TopStack
    public List<StackInstruction> top(OperationToken operationToken) {
        return constructInstructionList(operationToken, new PlusInstruction());
    }

    @BottomStack
    public List<StackInstruction> bottom(OperationToken operationToken) {
        return constructInstructionList(operationToken, new MinusInstruction());
    }

    List<StackInstruction> constructInstructionList(OperationToken operationToken, Instruction instruction) {
        List<StackInstruction> instructions = new ArrayList<StackInstruction>(1);
        StackInstruction stackInstruction = new StackInstruction(instruction, operationToken.getOperands());
        instructions.add(stackInstruction);
        return instructions;
    }

}
