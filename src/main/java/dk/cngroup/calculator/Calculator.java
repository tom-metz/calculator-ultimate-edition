package dk.cngroup.calculator;

import dk.cngroup.calculator.compiler.Compiler;
import dk.cngroup.calculator.output.Reporter;
import dk.cngroup.calculator.processor.Stack;
import dk.cngroup.calculator.processor.StackRun;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class Calculator {

    private final String fileName;
    private final Reporter reporter;

    protected Calculator(String fileName, Reporter reporter) {
        this.fileName = fileName;
        this.reporter = reporter;
    }

    protected void calculate() throws IOException {
        File inputFile = new File(fileName);
        List<String> inputLines = FileUtils.readLines(inputFile, "UTF-8");
        Compiler compiler = new Compiler("dk.cngroup.calculator.compiler", inputLines);
        Stack stack = compiler.compile();
        StackRun stackRun = new StackRun(stack);
        stackRun.execute();
        reporter.report(stackRun.getRegisterA());
    }

}
