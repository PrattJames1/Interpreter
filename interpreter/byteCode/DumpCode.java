package interpreter.byteCode;
import interpreter.VirtualMachine;

public class DumpCode extends ByteCode {

    public void execute(VirtualMachine virtualMachine) {
        if (this.argsList.get(0).equals("ON")) {
            virtualMachine.setDump(true);
        } else { // OFF
            virtualMachine.setDump(false);
        }
    }

    public String toString() {
        return "";
    }
}
