package dk.cngroup.calculator.processor;

import com.google.common.collect.ImmutableList;

import java.util.Iterator;
import java.util.List;

public class Stack implements Iterable<Instruction> {

    private final ImmutableList<Instruction> instructionList;

    Stack(List<Instruction> instructions) {
        instructionList = new ImmutableList.Builder<Instruction>().addAll(instructions).build();
    }

    @Override
    public Iterator<Instruction> iterator() {
        return instructionList.iterator();
    }

}
