package interpreter.byteCode;
import interpreter.VirtualMachine;

import java.util.ArrayList;
public class ReturnCode extends ByteCode {
    private int currentLine;

    @Override
    public void execute(VirtualMachine virtualMachine) {
        // Retrieves the current line to return to. Then
        // jumps to the corresponding label.
        try {
            currentLine = virtualMachine.getCurrentLine();
            int returnAddress = virtualMachine.getReturnAddress();
            virtualMachine.popFramePointer();
            virtualMachine.goToLine(returnAddress);
        }
        catch(Exception ignored) {}
    }

    @Override
    public String toString() {
        if (argsList.size() > 0) {
            return ("RETURN " + argsList.get(0) + "     EXIT f : " + currentLine);
        }
        else {
            return ("RETURN");
        }
    }
}
