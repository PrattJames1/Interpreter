package interpreter.byteCode;
import interpreter.VirtualMachine;

public class PopCode extends ByteCode {

    public void execute(VirtualMachine virtualMachine) {
        int numberOfPops = Integer.parseInt(argsList.get(0));
        for (int i = 0; i < numberOfPops; i++) {
            virtualMachine.popFromStack();
        }
    }

    @Override
    public String toString() {
        if (argsList.size() > 0) {
            return "POP " + argsList.get(0);
        }
        else {
            return ("POP");
        }
    }
}
