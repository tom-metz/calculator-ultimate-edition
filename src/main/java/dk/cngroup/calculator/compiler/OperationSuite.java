package dk.cngroup.calculator.compiler;

import com.google.common.collect.ImmutableList;
import dk.cngroup.calculator.annotation.BottomStack;
import dk.cngroup.calculator.annotation.IdentifyOperation;
import dk.cngroup.calculator.annotation.Operation;
import dk.cngroup.calculator.annotation.TopStack;
import dk.cngroup.calculator.processor.IncorrectInstructionException;
import org.reflections.Reflections;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.reflections.ReflectionUtils.getAllMethods;
import static org.reflections.ReflectionUtils.withAnnotation;

public class OperationSuite {

    private final List<OperationProxy> operationList;

    public OperationSuite(String path) throws IllegalAccessException, InstantiationException {
        operationList = scanForOperations(path);
    }

    public OperationProxy identifyOperation(OperationToken operationToken) throws IncorrectInstructionException, NumberFormatException, IllegalAccessException, InvocationTargetException {
        for (OperationProxy operation : operationList) {
            if (operation.invokeIdentify(operationToken)) {
                return operation;
            }
        }

        return null;
    }

    private List<OperationProxy> scanForOperations(String path) throws InstantiationException, IllegalAccessException {
        Reflections reflections = new Reflections(path);
        Set<Class<?>> operationClasses = reflections.getTypesAnnotatedWith(Operation.class);
        List<OperationProxy> operationProxyList = new ArrayList<OperationProxy>(operationClasses.size());

        for (Class operationClass: operationClasses) {
            Set<Method> identifyMethods = getAllMethods(operationClass, withAnnotation(IdentifyOperation.class));
            Method identifyOperation = identifyMethods.iterator().next();
            Set<Method> topStackMethods = getAllMethods(operationClass, withAnnotation(TopStack.class));
            Method topStack = topStackMethods.iterator().next();
            Set<Method> bottomStackMethods = getAllMethods(operationClass, withAnnotation(BottomStack.class));
            Method bottomStack = bottomStackMethods.iterator().next();
            boolean executable = ((Operation)operationClass.getAnnotation(Operation.class)).execute();
            OperationProxy operationProxy = new OperationProxy(operationClass, identifyOperation, topStack, bottomStack, executable);
            operationProxyList.add(operationProxy);
        }

        return new ImmutableList.Builder<OperationProxy>().addAll(operationProxyList).build();
    }

}
