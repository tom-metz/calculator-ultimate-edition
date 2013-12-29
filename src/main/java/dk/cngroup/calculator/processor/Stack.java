package dk.cngroup.calculator.processor;

import com.google.common.collect.ImmutableList;

import java.util.Iterator;
import java.util.List;

public class Stack implements Iterable<StackInstruction> {

    private final ImmutableList<StackInstruction> stackInstructions;

    public Stack(List<StackInstruction> stackInstructions) {
        this.stackInstructions = new ImmutableList.Builder<StackInstruction>().addAll(stackInstructions).build();
    }

    @Override
    public Iterator<StackInstruction> iterator() {
        return stackInstructions.iterator();
    }

}
