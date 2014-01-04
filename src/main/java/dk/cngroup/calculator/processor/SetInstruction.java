package dk.cngroup.calculator.processor;

public class SetInstruction implements Instruction {

    @Override
    public int execute(int register, int... operands) throws IncorrectInstructionException {
        int result = register;
        for (int operand : operands) {
            result = operand;
        }
        return result;
    }

}
