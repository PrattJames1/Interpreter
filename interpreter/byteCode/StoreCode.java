package interpreter.byteCode;

import interpreter.VirtualMachine;

import java.util.ArrayList;

public class StoreCode extends ByteCode {
    private String topOfStack;

    @Override
    public void execute(VirtualMachine virtualMachine) {
        // Store is used to move values from the top of the runtime stack to an offset
        // in the CURRENT FRAME. This offset starts from the beginning of the frame. So
        // it is not allowed to operate across frame boundaries.
        topOfStack = Integer.toString(virtualMachine.peekFromStack());
        int offset = Integer.parseInt(argsList.get(0));
        virtualMachine.store(offset);
    }

    @Override
    public String toString() {
        if (argsList.size() > 1) {
            return ("STORE " + Integer.parseInt(argsList.get(0)) + " " + argsList.get(1) + "     " + argsList.get(1)
                    + " = " + topOfStack);
        }
        else {
            return ("LOAD " + Integer.parseInt(argsList.get(0)));
        }
    }
}
