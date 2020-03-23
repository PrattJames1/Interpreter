package interpreter.byteCode;

import interpreter.VirtualMachine;

public class HaltCode extends ByteCode {

    public void execute(VirtualMachine virtualMachine) {
        // VM stop.
        virtualMachine.exitPlease();
    }

    @Override
    public String toString() { return "HALT"; }
}
