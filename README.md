The Calculator
==============

This project is an implementation of a coding exercise, used in a hiring process. It should give a feedback for interested candidates, how the "nearly ideal" solution should look like.

Instructions
------------

The submitted code should be of the quality we can expect during a normal working week.

Using maven or ant is not a requirement; however you are welcome to do so. Please provide instructions on how to run the application from the command line.

There is no requirement to cater for invalid input. As you will not have the opportunity to clarify requirements, feel free to note any assumptions in your submission.

You are welcome to use any external libraries you want. Include them in your submission.

It is important to insist on Test Driven Development, which means proceed first with tests and then implementation. You are required to show your ability to provide reasonable production quality code. Complete lack of test coverage will disqualify you immediately.

There are no tricks or gotchas: only implement the functionality described below. It is a simple problem to solve, but *code quality is as important as solving the problem itself*.

The problem
-----------

Write some code to calculate a result from a set of instructions. Instructions comprise of a keyword and a number that are separated by a space per line. Instructions are loaded from file and results are output to the screen. Any number of Instructions can be specified. Instructions can be any binary operators of your choice (e.g., add, divide, subtract, multiply etc). The instructions will ignore mathematical precedence. The last instruction should be “apply” and a number (e.g., “apply 3”). The calculator is then initialised with that number and the previous instructions are applied to that number.

*Example 1*

_Input from file_

    add 2
    multiply 3
    apply 3

_Output to screen_

    15

_Explanation_
(3 + 2) * 3 = 15

Implementation assumptions
--------------------------

_Technical assumptions_
* JDK 1.7
* Gradle 1.10
* Internet connection to download dependencies

_Logical assumptions_
* one operation can have multiple operands, e.g: `add 2 3`
* operation `apply <number>` is not mandatory at the end of the input, if it is missing, is is assumed to be `apply 0`
* all operations after `apply` are ignored, so there is only 1 `apply` operation allowed (or none)

Application execution
---------------------

You can simply use te gradle task:

`gradle run`

Without the parameter it uses the default inputs `src/main/resources/input.txt` or with a defined input file:

`gradle run -PappArgs='-f /path/to/input.txt'`

This is a way how to pass program arguments through gradle build system, it is equivalent to build the application JAR file and call:

`java -jar calculator-ultimate-edition.jar -f /path/to/input.txt`

The third way is to call gradle task, which creates the application distribution ZIP file, unzip it and run the application via delivered scripts:

`bin/calculator-ultimate-edition -f path/to/input.txt`
