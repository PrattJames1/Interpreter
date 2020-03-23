package interpreter;

import interpreter.byteCode.*;

import java.util.ArrayList;
import java.util.HashMap;

public class Program {
    private ArrayList<ByteCode> program;

    public Program(ArrayList<ByteCode> byteCodeList) {
        this.program = byteCodeList;
    }
    protected ByteCode getCode(int programCounter) {
        return program.get(programCounter);
    }

    /**
     * This function should go through the program and resolve all addresses.
     * Currently all labels look like LABEL <<num>>>, these need to be converted into
     * correct addresses so the VirtualMachine knows what to set the Program Counter
     * HINT: make note what type of data-structure ByteCodes are stored in.
     */
    public void resolveAddress() {
        HashMap<String, Integer> byteCodeMap = new HashMap<>();

        // Look for LABEL
        // Fill hashmap with label.
        // CAST the reference (changing its type)
        for (ByteCode byteCode : program) {
            if (byteCode instanceof LabelCode) {
                LabelCode label = (LabelCode) byteCode;
                String labelKey = label.getLabel();
                int labelValue = program.indexOf(label);
                byteCodeMap.put(labelKey, labelValue);
            }
        }

        // Look for GOTO, CALL, or FALSEBRANCH
        // Retrieves label. Where you should jump to.
        // Now that you know where you want to jump to, find the matching
        // label in your hashmap. It holds the value of the line number to
        // jump to.
        for (ByteCode byteCode : program) {
            if (byteCode instanceof GotoCode) {
                GotoCode goToPoint = (GotoCode) byteCode;
                String labelKey = goToPoint.getLabel();
                int lineNumberToJumpTo = byteCodeMap.get(labelKey);
                //System.out.println("Label key: " + labelKey + ", line number: " + lineNumberToJumpTo);
                goToPoint.setTo(lineNumberToJumpTo);
            }
            if (byteCode instanceof CallCode) {
                CallCode callPoint = (CallCode) byteCode;
                String labelKey = callPoint.getLabel();
                int lineNumberToJumpTo = byteCodeMap.get(labelKey);
                callPoint.setTo(lineNumberToJumpTo);
            }
            if (byteCode instanceof FalseBranchCode) {
                FalseBranchCode falseBranchPoint = (FalseBranchCode) byteCode;
                String labelKey = falseBranchPoint.getLabel();
                int lineNumberToJumpTo = byteCodeMap.get(labelKey);
                falseBranchPoint.setTo(lineNumberToJumpTo);
            }
        }
    }
}