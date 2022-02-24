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
        return "Showing results for: " + destination;//Showing results for Microsoft Store
    }

    @Override
    public String toShortString() {
        return "->" + destination;//Microsoft Store
    }
}
