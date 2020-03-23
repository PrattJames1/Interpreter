package interpreter.byteCode;

import interpreter.VirtualMachine;

import java.util.ArrayList;

public class LabelCode extends JumpCodes {

    public void execute(VirtualMachine virtualMachine) {}

    @Override
    public String getLabel() {
        return argsList.get(0);
    }

    // Dumping is optional.
}
