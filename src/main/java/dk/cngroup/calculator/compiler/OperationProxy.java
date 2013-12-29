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

    OperationProxy(Class operationClass, Method identify, Method topStack, Method bottomStack) throws IllegalAccessException, InstantiationException {
        this.operationClass = operationClass;
        this.identify = identify;
        this.topStack = topStack;
        this.bottomStack = bottomStack;
        this.instance = operationClass.newInstance();
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

}
