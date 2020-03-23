package interpreter.byteCode;
import interpreter.VirtualMachine;

public class LitCode extends ByteCode {

    public void execute(VirtualMachine virtualMachine) {
        // if machine is dumping, do System.out.println("Lit 0 j j");
        // But do dumping later.
        int intArgument = Integer.parseInt(argsList.get(0));
        virtualMachine.pushToStack(intArgument);
    }

    @Override
    public String toString() {
        if (argsList.size() > 1) {
            return ("LIT " + Integer.parseInt(argsList.get(0)) + " " + argsList.get(1) + "     int " + argsList.get(1));
        }
        else {
            return ("LIT " + Integer.parseInt(argsList.get(0)));
        }
    }
}
