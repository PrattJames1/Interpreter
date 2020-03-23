package interpreter.byteCode;

import interpreter.VirtualMachine;

import java.util.ArrayList;

public class WriteCode extends ByteCode {

    public void execute(VirtualMachine virtualMachine) {
        virtualMachine.peekFromStack();
    }

    @Override
    public String toString() {
        return ("WRITE");
    }
}
