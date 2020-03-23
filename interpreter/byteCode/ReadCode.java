package interpreter.byteCode;
import interpreter.VirtualMachine;
import java.util.Scanner;
import java.io.InputStream;

public class ReadCode extends ByteCode {

    public void execute(VirtualMachine virtualMachine) {
        Scanner scanner = new Scanner(System.in);
        String input;
        int inputInt = 0;
        boolean running = true;

        while (running) {
            try {
                System.out.println("Please enter an integer : ");
                input = scanner.nextLine();
                inputInt = Integer.parseInt(input);
                running = false;

            } catch (NumberFormatException ex) {
                System.out.println("Invalid input. Please enter an integer : ");
            }
        }
        virtualMachine.pushToStack(inputInt);
    }

    @Override
    public String toString() {
        return ("READ");
    }
}
