package interpreter;

import interpreter.byteCode.ByteCode;
import java.util.Stack;

public class VirtualMachine {

    // Used to store all variables in the program.
    private RunTimeStack   runTimeStack;
    // Used to store return addresses for each called function (excluding main)
    private Stack<Integer> returnAddress;
    // Reference to the program object where all ByteCodes are stored.
    private Program        program;
    // The program counter (current ByteCode being executed)
    private int            programCounter;
    // Used to determine whether the VirtualMachine should be executing ByteCodes.
    private boolean        isRunning = true;
    // Need a dump flag.
    private boolean isDump = false;

    public void executeProgram(){
        programCounter = 0;
        runTimeStack = new RunTimeStack();
        returnAddress = new Stack <>();
        isRunning = true;

        while(isRunning) {
            ByteCode code = program.getCode(programCounter);
            code.execute(this);
            if (isDump && !code.toString().equals("")) {
                System.out.println(code.toString());
                runTimeStack.dump();
            }
            programCounter++;
        }
    }

    public int getCurrentLine() {
        return programCounter;
    }

    public String getCurrentFrame() {
        return runTimeStack.getFrame();
    }

    public void goToLine(int lineToGoTo) {
        this.programCounter = lineToGoTo;
    }

    public void addReturnAddress(int returnAddress) {
        this.returnAddress.push(returnAddress);
    }

    public int getReturnAddress() {
        return this.returnAddress.pop();
    }

    public void pushToStack(int integerToPush) {
        runTimeStack.push(integerToPush);
    }

    public int popFromStack() {
        return runTimeStack.pop();
    }

    public int peekFromStack() {
        return runTimeStack.peek();
    }

    public void store(int offset) {
        runTimeStack.store(offset);
    }

    public void load(int offset) {
        runTimeStack.load(offset);
    }

    public void pushFramePointer(int offset) {
        runTimeStack.newFrameAt(offset);
    }

    public void popFramePointer() {
        runTimeStack.popFrame();
    }

    public void setDump(boolean shouldDump) {
        this.isDump = shouldDump;
    }

    public void exitPlease() {
        isRunning = false;
    }

    protected VirtualMachine(Program program) {
        this.program = program;
    }
}
