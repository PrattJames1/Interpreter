package interpreter.byteCode;
import interpreter.VirtualMachine;

public class BopCode extends ByteCode {

    public void execute(VirtualMachine virtualMachine) {
        int operandTwo = virtualMachine.popFromStack();
        int operandOne = virtualMachine.popFromStack();
        String operator = argsList.get(0);
        int answer = 0;

        if (operator.equals("+")) answer = operandOne + operandTwo;
        else if (operator.equals("-")) answer = operandOne - operandTwo;
        else if (operator.equals("/")) answer = operandOne / operandTwo;
        else if (operator.equals("*")) answer = operandOne * operandTwo;
        else if (operator.equals("<=") && (operandOne <= operandTwo)) { answer = 1; }
        else if (operator.equals(">=") && (operandOne >= operandTwo)) { answer = 1; }
        else if (operator.equals("<") && (operandOne < operandTwo)) { answer = 1; }
        else if (operator.equals(">") && (operandOne > operandTwo)) { answer = 1; }
        else if (operator.equals("==") || operator.equals("&")) {
            if (operandOne == operandTwo) { answer = 1; }}
        else if (operator.equals("!=") || operator.equals("|")) {
            if (operandOne != operandTwo) { answer = 1; }}
        virtualMachine.pushToStack(answer);
    }

    @Override
    public String toString() {
        if (argsList.size() > 0) {
            return "BOP " + argsList.get(0);
        }
        else {
            return "BOP ";
        }
    }
}
