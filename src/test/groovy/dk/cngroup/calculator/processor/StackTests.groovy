package dk.cngroup.calculator.processor

import com.google.common.collect.UnmodifiableIterator
import spock.lang.Specification

class StackTests extends Specification {

    private List<StackInstruction> instructions

    void setup() {
        instructions = new ArrayList<StackInstruction>(3)
        instructions.add(new StackInstruction(new PlusInstruction(), 5))
        instructions.add(new StackInstruction(new MinusInstruction(), 1))
        instructions.add(new StackInstruction(new MultiplicationInstruction(), 2))
    }

    void 'Created Stack instance has 3 elements'() {
        setup:
        Stack stack = new Stack(instructions)
        Iterator<StackInstruction> iterator = stack.iterator()

        when:
        StackInstruction instruction = iterator.next()

        then:
        5 == instruction.execute(0)

        when:
        instruction = iterator.next()

        then:
        4 == instruction.execute(5)

        when:
        instruction = iterator.next()

        then:
        8 == instruction.execute(4)
        !iterator.hasNext();
    }

    void 'Stack Iterator does not allow removing elements'() {
        setup:
        Stack stack = new Stack(instructions);
        Iterator<StackInstruction> iterator = stack.iterator()

        when: 'remove() is called'
        iterator.hasNext()
        iterator.remove()

        then: 'UnsupportedOperationException must be thrown'
        thrown UnsupportedOperationException
        iterator instanceof UnmodifiableIterator
    }

}
