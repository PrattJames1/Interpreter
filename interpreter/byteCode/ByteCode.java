package interpreter.byteCode;

import interpreter.VirtualMachine;

import java.util.ArrayList;

public abstract class ByteCode {
    ArrayList<String> argsList = new ArrayList<>();

    public void init(ArrayList<String> args) {
        this.argsList = args;
    };

    public abstract void execute(VirtualMachine virtualMachine);
}


