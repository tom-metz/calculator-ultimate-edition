package dk.cngroup.calculator;

import dk.cngroup.calculator.compiler.Compiler;
import dk.cngroup.calculator.output.Reporter;
import dk.cngroup.calculator.output.StandardOutputReporter;
import dk.cngroup.calculator.processor.Stack;
import dk.cngroup.calculator.processor.StackRun;
import org.apache.commons.cli.*;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class Calculator {

    private static final Logger logger = LoggerFactory.getLogger(Calculator.class);

    private final String fileName;
    private final Reporter reporter;

    Calculator(String fileName, Reporter reporter) {
        this.fileName = fileName;
        this.reporter = reporter;
    }

    void calculate() throws IOException {
        File inputFile = new File(fileName);
        List<String> inputLines = FileUtils.readLines(inputFile, "UTF-8");
        Compiler compiler = new Compiler("dk.cngroup.calculator.compiler", inputLines);
        Stack stack = compiler.compile();
        StackRun stackRun = new StackRun(stack);
        stackRun.execute();
        reporter.report(stackRun.getRegisterA());
    }

    public static void main(String[] args) {

        try {
            Option fileOption = new Option("f", "file", true, "input file with operations");
            Options options = new Options();
            options.addOption(fileOption);

            CommandLineParser parser = new BasicParser();
            CommandLine commandLine = parser.parse(options, args);

            String fileName = Calculator.class.getClassLoader().getResource("input.txt").getFile();
            if (commandLine.hasOption("f")) {
                fileName = commandLine.getOptionValue("f");
            } else {
                HelpFormatter formatter = new HelpFormatter();
                formatter.printHelp("calculator", options);
                System.out.println("No input file defined, using default \"input.txt\"");
            }

            Calculator calculator = new Calculator(fileName, new StandardOutputReporter());
            calculator.calculate();

        } catch (IOException ioe) {
            logger.error("A problem with the input file occurs: {}", ioe.getMessage());
        } catch (ParseException pe) {
            logger.error("A problem with parsing command line options occurs: {}", pe.getMessage());
        }
    }

}
