package dk.cngroup.calculator.processor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StackRun {

    private static final Logger logger = LoggerFactory.getLogger(StackRun.class);

    private final Stack stack;
    private int registerA = 0;

    public StackRun(Stack stack) {
        this.stack = stack;
    }

    public void execute() {
        try {
            for (StackInstruction stackInstruction : stack) {
                registerA = stackInstruction.execute(registerA);
            }
        } catch (IncorrectInstructionException iie) {
            logger.error("Stack execution was stopped because of incorrect instruction", iie);
        }
    }

    public int getRegisterA() {
        return registerA;
    }

}
