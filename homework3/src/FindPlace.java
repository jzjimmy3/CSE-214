import java.util.Scanner;

public class FindPlace implements Command {
    private String destination;
    public FindPlace(Scanner scanner){
        String input = scanner.nextLine();
        this.destination = input;
    }
    @Override
    public boolean validCommand(CommandStack stack) {
        return false;
    }

    @Override
    public String toString() {
        // toString is supposed to be for Current Screen
        return "Showing Results for " + destination;//Showing results for Microsoft Store
    }

    @Override
    public String toShortString() {
        //toShortString is supposed to be for Stack
        return "F: " + destination;//Microsoft Store
    }
}
