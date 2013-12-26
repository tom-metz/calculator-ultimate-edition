package dk.cngroup.calculator.annotation

import spock.lang.Specification

class OperationTests extends Specification {

    void 'Usage without param should set default value'() {
        when: 'Class is annotated with @Operation with no parameter'

        then: 'default "false" value is set'
        !OperationAnnotationTestClass1.class.getAnnotation(Operation.class).execute()
    }

    void 'Usage with param "true" should set value to "true"'() {
        when: 'Class is annotated with @Operation(execute=true)'

        then: 'value is set to to "true"'
        OperationAnnotationTestClass2.class.getAnnotation(Operation.class).execute()
    }

    @Operation
    class OperationAnnotationTestClass1 {
    }

    @Operation(execute = true)
    class OperationAnnotationTestClass2 {
    }

}
