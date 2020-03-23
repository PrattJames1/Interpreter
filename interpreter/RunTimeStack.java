package interpreter;

import java.util.ArrayList;
import java.util.Stack;

public class RunTimeStack {
    /**
     *  The stack is used to record the beginning of each activation
     *  record (frame) when calling functions.
     *  The ArrayList is used to represent the runtime stack. It will
     *  be an ArrayList because we will need to access ALL locations of
     *  the runtime stack.
     */

    private ArrayList<Integer> runTimeStack;
    private Stack<Integer>     framePointer;

    public RunTimeStack() {
        runTimeStack = new ArrayList<>();
        framePointer = new Stack<>();
        // Add initial Frame Pointer, main is the entry
        // point of our language, so its frame pointer is 0.
        framePointer.add(0);
    }

    public void push(int pushValue) {
        this.runTimeStack.add(pushValue);
    }

    public int pop() {
        int poppedValue = 0;
        if (runTimeStack.size() > 0) {
            poppedValue = this.runTimeStack.remove(runTimeStack.size() - 1);
        }
        return poppedValue;
    }

    public int peek() {
        int peekedValue = this.runTimeStack.get(runTimeStack.size()-1);
        System.out.println(peekedValue);
        return peekedValue;
    }

    // Grabs element at top of runTimeStack, stores in given offset in runTimeStack.
    // Determines if offset is larger than your currentFrame.
    // currentFrame is the size of the RTS minus the last element in our FPS.
    // For example if our RTS is size 5 and our last element in our FPS is
    // 4, we want our currentFrame to be 1, not 4.
    public int store(int offset) {
        int peekedValue = 0;
        int currentFrameStart = framePointer.get(framePointer.size()-1);
        int currentFrameSize = runTimeStack.size()-currentFrameStart;
        if (offset <= currentFrameSize) {
            peekedValue = this.runTimeStack.remove(runTimeStack.size() - 1);
            currentFrameSize--;
            this.runTimeStack.set(currentFrameStart + offset, peekedValue);
        }
        return peekedValue;
    }

    // Grabs element at OFFSET of runTimeStack, pushes to top of the runTimeStack.
    public void load(int offset) {
        int peekedValue = 0;
        int currentFrameStart = framePointer.get(framePointer.size()-1);
        int currentFrameSize = runTimeStack.size()-currentFrameStart;
        if (offset <= currentFrameSize) {
            peekedValue = this.runTimeStack.get(currentFrameStart + offset);
            push(peekedValue);
        }
    }

    public void newFrameAt(int offset) {
        int convertedOffset = runTimeStack.size() - offset;
        if (convertedOffset >= 0) {
            framePointer.add(convertedOffset);
        }
    }

    public String getFrame() {
        String returnValue = "";
        int currentFrameStartPointer = framePointer.get(framePointer.size()-1);
        for (int i = currentFrameStartPointer; i < runTimeStack.size(); i++) {
            int currentElement = runTimeStack.get(i);
            returnValue = returnValue + " " + (currentElement);
        }
        return returnValue;
    }

    // Gets the returnValue at top of runTimeStack.
    // Removes all elements in the current frame.
    // Pops all values inside the current frame.
    // Pops the framePointer. Finally, return the returnValue.
    public void popFrame() {
        int returnValue = runTimeStack.get(runTimeStack.size()-1);
        int currentFrame = framePointer.get(framePointer.size()-1);
        int leftover = runTimeStack.size() - currentFrame;
        for (int i = 0; i < leftover; i++) {
            pop();
        }
        framePointer.pop();
        push(returnValue);
    }

    public void dump() {
        //System.out.println("Current runTimeStack: " + runTimeStack);
        //System.out.println("Current framePointer stack: " + framePointer);

        String dump = "";

        if (runTimeStack.size() == 0) {
            System.out.println("[]");
            return;
        }

        // Use an arrayList b/c you don't know how long the array will be.
        // Obtain values of top 2 framePointers.
        // Print individual frames. Find offset.
        for (int i=0; i<framePointer.size(); i++) {
            ArrayList<Integer> currentFrame = new ArrayList<>();
            int lowFramePointer = framePointer.get(i);
                // ternary         (      IF CONDITION         ) ?        IF TRUE              ELSE
            int highFramePointer = (i == framePointer.size() -1) ? runTimeStack.size() : framePointer.get(i+1);

            for (int j = lowFramePointer; j < highFramePointer; j++) {
                int poppedValue = runTimeStack.get(j);
                currentFrame.add(poppedValue);
            }
            dump = dump + currentFrame.toString();
        }
        dump = dump.trim();
        System.out.println(dump);
    }
}