
package interpreter;

import interpreter.byteCode.ByteCode;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class ByteCodeLoader {

    private BufferedReader byteSource;

    /**
     * Constructor Simply creates a buffered reader.
     */
    public ByteCodeLoader(String file) throws IOException {
        this.byteSource = new BufferedReader(new FileReader(file));
    }
    /**
     * This function should read one line of source code at a time.
     * For each line it should:
     *      Tokenize string to break it into parts. Can also use the split function in the String class.
     *      Grab THE correct class name for the given ByteCode from CodeTable
     *      Create an instance of the ByteCode class name returned from code table.
     *      Parse any additional arguments for the given ByteCode and send them to
     *      the newly created ByteCode instance via the init function.
     */
    public Program loadCodes() {
        String line;
        ArrayList<ByteCode> byteCodeList = new ArrayList<>();

        try {
            // This while loop tells whether or not the stream is ready to be read.
            // Then it reads a new line, then tokenizes the string and breaks it
            // into parts, putting them into an ArrayList.
            while (this.byteSource.ready()) {
                line = this.byteSource.readLine();
                String[] itemsArray = line.split("\\s+");
                List<String> itemsArrayList = Arrays.asList(itemsArray);

                ArrayList<String> argumentList = new ArrayList<>(itemsArrayList);
                argumentList.remove(0);

                // Create an instance of the ByteCode class name returned from code table.
                // This involves creating the class name, as well as getting its constructor,
                // then creating a new instance.
                String className = CodeTable.getClassName(itemsArrayList.get(0));
                Class classBlueprint = Class.forName("interpreter.byteCode." + className);
                Constructor codeConstructor = classBlueprint.getDeclaredConstructor();

                ByteCode byteCode = (ByteCode)codeConstructor.newInstance();

                // init byteCode. Add bytecode to byteCodeList.
                byteCode.init(argumentList);
                byteCodeList.add(byteCode);
            }
            // Pass byteCodeList to program object. Resolve entire program's addresses,
            // then return the program.
            Program program = new Program(byteCodeList);
            program.resolveAddress();
            return program;
        }
        catch (Exception e) {
            System.out.println(e);
            e.printStackTrace();
            System.exit(-1);
        }
        return null;
    }
}
