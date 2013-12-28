package dk.cngroup.calculator.compiler;

import java.util.Arrays;

public class OperationToken {

    private String operation;
    private int[] operands;

    OperationToken(String raw) {
        raw = raw.trim();
        if (!raw.isEmpty()) {
            String[] tokens = raw.split("\\s+");
            this.operation = tokens[0].toLowerCase();
            if (tokens.length > 1) {
                this.operands = convertOperands(Arrays.copyOfRange(tokens, 1, tokens.length));
            } else {
                this.operands = new int[] {};
            }
        } else  {
            this.operands = new int[] {};
        }
    }

    public String getOperation() {
        return operation;
    }

    public int[] getOperands() {
        return operands;
    }

    private int[] convertOperands(String[] operands) {
        int[] result = new int[operands.length];
        for (int i = 0; i < operands.length; i++) {
            result[i] = Integer.valueOf(operands[i]);
        }
        return result;
    }

}
