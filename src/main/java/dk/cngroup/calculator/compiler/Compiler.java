package dk.cngroup.calculator.compiler;

import dk.cngroup.calculator.processor.IncorrectInstructionException;
import dk.cngroup.calculator.processor.Stack;
import dk.cngroup.calculator.processor.StackInstruction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

public class Compiler {

    private static final Logger logger = LoggerFactory.getLogger(Compiler.class);

    private final String operationPackage;
    private final List<String> inputLines;

    public Compiler(String operationPackage, List<String> inputLines) {
        this.operationPackage = operationPackage;
        this.inputLines = inputLines;
    }

    public Stack compile() {
        int lineCounter = 0;
        try {
            OperationSuite operationSuite = new OperationSuite(operationPackage);
            List<StackInstruction> stackInstructionList = new ArrayList<StackInstruction>(inputLines.size());

            for (String line : inputLines) {
                lineCounter++;
                if (line.trim().isEmpty()) {
                    continue;
                }

                OperationToken operationToken = new OperationToken(line);
                OperationProxy operation = operationSuite.identifyOperation(operationToken);
                if (operation != null) {
                    stackInstructionList.addAll(0, operation.invokeTopStack(operationToken));
                    stackInstructionList.addAll(operation.invokeBottomStack(operationToken));

                    if (operation.isExecutable()) {
                        break;
                    }
                } else {
                    logger.error("Invalid operation at line {}", lineCounter);
                }
            }

            return new Stack(stackInstructionList);

        } catch (IncorrectInstructionException iie) {
            logger.error("Incorrect instruction at line {}", lineCounter, iie);
        } catch (NumberFormatException nfe) {
            logger.error("Incorrect operand at line {}", lineCounter, nfe);
        } catch (IllegalAccessException iae) {
            logger.error("Cannot access operation implementation at line {}. Probably missing operation method.", lineCounter, iae);
        } catch (InvocationTargetException ite) {
            logger.error("Cannot invoke operation implementation at line {}. The invoked method throws an exception.", lineCounter, ite);
        } catch (InstantiationException ie) {
            logger.error("Cannot instantiate operation implementation at line {}.", lineCounter, ie);
        }

        return null;
    }

}
