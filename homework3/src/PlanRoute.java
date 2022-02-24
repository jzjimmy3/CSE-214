import java.util.Scanner;

public class PlanRoute {
    private String source;
    private String destination;

    public PlanRoute(Scanner scanner){
        String input = scanner.nextLine();
        this.destination = input;
    }

}
