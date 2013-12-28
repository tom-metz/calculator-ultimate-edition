package dk.cngroup.calculator.processor

import com.google.common.collect.UnmodifiableIterator
import spock.lang.Specification

class StackTests extends Specification {

    private List<Instruction> instructions;

    void setup() {
        instructions = new ArrayList<Instruction>(3);
        instructions.add(new PlusInstruction());
        instructions.add(new MinusInstruction());
        instructions.add(new MultiplicationInstruction());
    }

    void 'Created Stack instance has 3 elements'() {
        setup:
        Stack stack = new Stack(instructions);
        Iterator<Instruction> iterator = stack.iterator();

        when:
        Instruction instruction = iterator.next();

        then:
        instruction instanceof PlusInstruction;

        when:
        instruction = iterator.next();

        then:
        instruction instanceof MinusInstruction;

        when:
        instruction = iterator.next();

        then:
        instruction instanceof MultiplicationInstruction;
        !iterator.hasNext();
    }

    void 'Stack Iterator does not allow removing elements'() {
        setup:
        Stack stack = new Stack(instructions);
        Iterator<Instruction> iterator = stack.iterator();

        when: 'remove() is called'
        iterator.hasNext();
        iterator.remove();

        then: 'UnsupportedOperationException must be thrown'
        thrown(UnsupportedOperationException);
        iterator instanceof UnmodifiableIterator;
    }

}
