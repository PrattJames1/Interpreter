package interpreter.byteCode;

import interpreter.VirtualMachine;

public class CallCode extends JumpCodes {
    String currentFrame = "";

    @Override
    public void execute(VirtualMachine virtualMachine) {
        // Retrieves the current line to return to. Stores in argsList. Then
        // jumps to the corresponding label.
        int currentLine = virtualMachine.getCurrentLine();
        virtualMachine.addReturnAddress(currentLine);
        virtualMachine.goToLine(jumpTo);
        currentFrame = virtualMachine.getCurrentFrame();
    }

    @Override
    public String toString() {
        if (argsList.size() > 0) {
            String removedCarrots = argsList.get(0).split("<")[0];
            return "CALL " + removedCarrots + "     " + removedCarrots + "(" + currentFrame + " )";
        }
        else {
            return "CALL";
        }
    }
}
