package dk.cngroup.calculator.output;

public class StandardOutputReporter implements Reporter {

    @Override
    public void report(int result) {
        System.out.printf("Calculation result: %d", result);
    }

}
