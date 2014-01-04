package dk.cngroup.calculator.processor;

public class PrintInstruction implements Instruction {

    @Override
    public int execute(int register, int... operands) throws IncorrectInstructionException {
        System.out.println(register);
        return register;
    }

}
