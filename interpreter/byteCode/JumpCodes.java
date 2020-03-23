package interpreter.byteCode;

import interpreter.VirtualMachine;

public abstract class JumpCodes extends ByteCode {
    // have a jumpto int and a setto integer.
    // when you go through the for loop, look for the matching index.
    // get index and save to the jump to. Use that when you're setting
    // the program counter.
    int jumpTo;

    public abstract void execute(VirtualMachine virtualMachine);
    public String getLabel() {
        return argsList.get(0);
    };

    public void setTo(int lineNumber) {
        this.jumpTo = lineNumber;
    }
}