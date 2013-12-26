package dk.cngroup.calculator.processor;

public interface Instruction {

    int execute(int register, int ... operands) throws IncorrectInstructionException;

}
