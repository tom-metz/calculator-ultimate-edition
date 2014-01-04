package dk.cngroup.calculator.compiler
import dk.cngroup.calculator.processor.Stack
import dk.cngroup.calculator.processor.StackInstruction
import spock.lang.Specification

class CompilerTests extends Specification {

    void 'List of test operations at the input generates correct Stack'() {
        setup:
        List<String> inputLines = new ArrayList<String>(4);
        inputLines.add 'remove 1'
        inputLines.add 'multiply 2'
        inputLines.add 'divide 4'
        inputLines.add 'apply 9'
        Compiler compiler = new Compiler('dk.cngroup.calculator.compiler', inputLines)

        when: 'input is compiled'
        Stack stack = compiler.compile()
        Iterator<StackInstruction> instructionIterator = stack.iterator()
        StackInstruction instruction1 = instructionIterator.next()
        StackInstruction instruction2 = instructionIterator.next()
        StackInstruction instruction3 = instructionIterator.next()
        StackInstruction instruction4 = instructionIterator.next()

        then: 'correct Stack must be generated'
        9 == instruction1.execute(0)
        8 == instruction2.execute(9)
        16 == instruction3.execute(8)
        4 == instruction4.execute(16)
    }

    void 'List of test operations without APPLY at the input generates correct Stack'() {
        setup:
        List<String> inputLines = new ArrayList<String>(4);
        inputLines.add 'add 9'
        inputLines.add 'remove 1'
        inputLines.add 'multiply 2'
        inputLines.add 'divide 4'
        Compiler compiler = new Compiler('dk.cngroup.calculator.compiler', inputLines)

        when: 'input is compiled'
        Stack stack = compiler.compile()
        Iterator<StackInstruction> instructionIterator = stack.iterator()
        StackInstruction instruction1 = instructionIterator.next()
        StackInstruction instruction2 = instructionIterator.next()
        StackInstruction instruction3 = instructionIterator.next()
        StackInstruction instruction4 = instructionIterator.next()

        then: 'correct Stack must be generated'
        9 == instruction1.execute(0)
        8 == instruction2.execute(9)
        16 == instruction3.execute(8)
        4 == instruction4.execute(16)
    }

    void 'Pperations after APPLY at the input are not generated into the Stack'() {
        setup:
        List<String> inputLines = new ArrayList<String>(4);
        inputLines.add 'remove 1'
        inputLines.add 'divide 4'
        inputLines.add 'apply 9'
        inputLines.add 'multiply 2'
        Compiler compiler = new Compiler('dk.cngroup.calculator.compiler', inputLines)

        when: 'input is compiled'
        Stack stack = compiler.compile()
        Iterator<StackInstruction> instructionIterator = stack.iterator()
        StackInstruction instruction1 = instructionIterator.next()
        StackInstruction instruction2 = instructionIterator.next()
        StackInstruction instruction3 = instructionIterator.next()

        then: 'correct Stack with only 3 instructions  must be generated'
        !instructionIterator.hasNext()
        9 == instruction1.execute(0)
        8 == instruction2.execute(9)
        2 == instruction3.execute(8)
    }

}
