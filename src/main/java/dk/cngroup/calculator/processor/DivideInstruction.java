package dk.cngroup.calculator.processor;

public class DivideInstruction implements Instruction {

    @Override
    public int execute(int register, int... operands) throws IncorrectInstructionException{
        int result = register;
        for (int operand : operands) {
            if (operand == 0) {
                throw new IncorrectInstructionException("Division by zero!");
            }
            result /= operand;
        }
        return result;
    }

}
