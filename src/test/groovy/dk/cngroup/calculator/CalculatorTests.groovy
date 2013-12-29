package dk.cngroup.calculator

import dk.cngroup.calculator.output.Reporter
import spock.lang.Specification

class CalculatorTests extends Specification {

    void 'Example data 01 should report correct outcome'() {
        setup:
        String fileName = this.getClass().getClassLoader().getResource("test_example_01.txt").getFile()
        TestReporter testReporter = new TestReporter()
        Calculator calculator = new Calculator(fileName, testReporter)

        when: 'input file is processed'
        calculator.calculate()

        then: 'test reporter must contain correct result'
        6 == testReporter.result
    }

    private class TestReporter implements Reporter {

        private int result

        @Override
        void report(int result) {
            this.result = result
        }

        int getResult() {
            this.result
        }

    }

}
