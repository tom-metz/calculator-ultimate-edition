package dk.cngroup.calculator.processor

import dk.cngroup.calculator.compiler.Compiler

import spock.lang.Specification

class StackRunTests extends Specification {

    void 'List of test operations at the input is correctly executed'() {
        setup:
        List<String> inputLines = new ArrayList<String>(4);
        inputLines.add 'add 9'
        inputLines.add 'remove 1'
        inputLines.add 'multiply 2'
        inputLines.add 'divide 4'
        Compiler compiler = new Compiler('dk.cngroup.calculator.compiler', inputLines)
        Stack stack = compiler.compile()
        StackRun stackRun = new StackRun(stack);

        when: 'input is executed'
        stackRun.execute()

        then: 'correct Stack must be generated'
        4 == stackRun.getRegisterA()
    }

}
