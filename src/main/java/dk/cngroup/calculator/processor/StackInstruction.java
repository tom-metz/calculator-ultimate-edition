package dk.cngroup.calculator.processor;

public class StackInstruction {

    private final Instruction instruction;
    private final int[] operands;

    public StackInstruction(Instruction instruction, int[] operands) {
        this.instruction = instruction;
        this.operands = operands;
    }

    public int execute(int register) throws IncorrectInstructionException {
        return instruction.execute(register, operands);
    }

}
