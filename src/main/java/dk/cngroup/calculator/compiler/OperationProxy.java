package dk.cngroup.calculator.compiler;

import dk.cngroup.calculator.processor.StackInstruction;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

class OperationProxy {

    final Class operationClass;
    final Method identify;
    final Method topStack;
    final Method bottomStack;
    final Object instance;
    final boolean executable;

    OperationProxy(Class operationClass, Method identify, Method topStack, Method bottomStack, boolean executable) throws IllegalAccessException, InstantiationException {
        this.operationClass = operationClass;
        this.identify = identify;
        this.topStack = topStack;
        this.bottomStack = bottomStack;
        this.instance = operationClass.newInstance();
        this.executable = executable;
    }

    boolean invokeIdentify(OperationToken operationToken) throws InvocationTargetException, IllegalAccessException {
        return (Boolean)identify.invoke(instance, operationToken);
    }

    List<StackInstruction> invokeTopStack(OperationToken operationToken) throws InvocationTargetException, IllegalAccessException {
        return (List<StackInstruction>)topStack.invoke(instance, operationToken);
    }

    List<StackInstruction> invokeBottomStack(OperationToken operationToken) throws InvocationTargetException, IllegalAccessException {
        return (List<StackInstruction>)bottomStack.invoke(instance, operationToken);
    }

    public boolean isExecutable() {
        return executable;
    }

}
