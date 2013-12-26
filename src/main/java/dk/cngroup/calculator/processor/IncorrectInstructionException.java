package dk.cngroup.calculator.processor;

public class IncorrectInstructionException extends Exception {

    public IncorrectInstructionException() {
        super();
    }

    public IncorrectInstructionException(String message) {
        super(message);
    }

}
