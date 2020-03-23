package interpreter.byteCode;

import interpreter.VirtualMachine;

import java.util.ArrayList;

public class FalseBranchCode extends JumpCodes {

    public void execute(VirtualMachine virtualMachine) {
        int label = virtualMachine.popFromStack();
        if (label == 0) {
            virtualMachine.goToLine(jumpTo);
        }
    }

    @Override
    public String toString() {
        if (argsList.size() > 0) {
            return "FALSEBRANCH " + argsList.get(0);
        }
        else {
            return "FALSEBRANCH ";
        }
    }
}
