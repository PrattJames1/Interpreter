package interpreter.byteCode;

import interpreter.VirtualMachine;

public class GotoCode extends JumpCodes {

    @Override
    public void execute(VirtualMachine virtualMachine) {
        // Go through the label hashmap, find the same label, then
        // print the corresponding value.
        virtualMachine.goToLine(jumpTo);
    }

    @Override
    public String toString() {
        if (argsList.size() > 0) {
            return "GOTO " + argsList.get(0);
        }
        else {
            return "GOTO";
        }
    }
}
