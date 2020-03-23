package interpreter.byteCode;

import interpreter.VirtualMachine;

import java.util.ArrayList;

public class ArgsCode extends ByteCode {

    public void execute(VirtualMachine virtualMachine) {
        // Will setup how many arguments a function has. numberOfValues is reserved for
        // the next function call. It'll determine how many values from top of RTS
        // will be part of a newly created activation frame.
        int numberOfValues = Integer.parseInt(argsList.get(0));
        virtualMachine.pushFramePointer(numberOfValues);
    }

    @Override
    public String toString() {
        if (argsList.size() > 0) {
            return "ARGS " + argsList.get(0);
        }
        else {
            return "ARGS";
        }
    }
}