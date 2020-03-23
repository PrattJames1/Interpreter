package interpreter.byteCode;

import interpreter.VirtualMachine;

import java.util.ArrayList;

public class LoadCode extends ByteCode {

    @Override
    public void execute(VirtualMachine virtualMachine) {
        String identifier;
        int offset;

        // Find offset.
        offset = Integer.parseInt(argsList.get(0));

        if (argsList.size() > 1) {
            identifier = argsList.get(1);
        }

        virtualMachine.load(offset);
    }

    @Override
    public String toString() {
        if (argsList.size() > 1) {
            return ("LOAD " + Integer.parseInt(argsList.get(0)) + " " + argsList.get(1));
        }
        else {
            return ("LOAD " + Integer.parseInt(argsList.get(0)));
        }
    }
}
