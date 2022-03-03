import java.util.Scanner;

public class iCatchUp {
    public static boolean quitValue = true;
    public static Scanner input = new Scanner(System.in);
    public static Application apps = new Application();

    public static void main(String[] args) throws Exception {
        System.out.println("\nWelcome to the iPhony pocket telegraph simulator.\nYou are on the home screen.\n");
        Application.getMapStack().push(Application.home);
        Application.getSafariStack().push(Application.home);
        while(quitValue){
            currentScreen();
        }
    }

    public static void homeOptions() throws Exception {
        System.out.println("Home Options: \n" +
                "   S) Safari\n" +
                "   M) Maps\n" +
                "   Q) Quit\n"
        );
        System.out.print("Please select an option: ");
        String inputVal = input.next().toUpperCase();
        try{
            if(inputVal.matches("[^SMQ]")){
                throw new Command.InvalidCommandException("\nInvalid Command for Home Options\n");
            }
        }catch (Command.InvalidCommandException e){
            System.out.println(e.getMessage());
        }
        switch (inputVal) {
            case "S":
                Application.setCurrentScreen("S");
                Application.setCurrentStack(apps.stackValue());
                printStack();
                safariOptions();
                break;
            case "M":
                Application.setCurrentScreen("M");
                Application.setCurrentStack(apps.stackValue());
                printStack();
                mapOptions();
                break;
            case "Q":
                quitValue = false;
                System.out.println("Sorry to see you go, tell the iPod I said hi!");
                break;
        }
    }
    public static void mapOptions() throws Exception {
        Application.setCurrentScreen("M");

        System.out.println("Map Options: \n" +
                "   F) Find a place\n" +
                "   P) Plan a route\n" +
                "   N) Start Navigation\n" +
                "   H) Home Screen\n" +
                "   S) Switch to Safari\n" +
                "   B) Back\n"
        );
        System.out.print("Please select an option: ");
        apps.readCommand(new Scanner(System.in));
        printStack();
    }
    public static void safariOptions() throws Exception {
        Application.setCurrentScreen("S");
        System.out.println(
                "Safari Options: \n" +
                "   G) Google Something\n" +
                "   F) Go to a favorite (bookmark)\n" +
                "   L) Follow a link\n" +
                "   H) Home Screen\n" +
                "   S) Switch to Maps\n" +
                "   B) Back\n"
        );
        System.out.print("Please select an option: ");
        apps.readCommand(new Scanner(System.in));
        printStack();
    }

    public static void currentScreen() throws Exception {
        switch(Application.getCurrentScreen()){
            case "H": homeOptions();
                break;
            case "S": safariOptions();
                break;
            case "M": mapOptions();
                break;
            default:break;
        }
    }

    public static void printStack(){
        System.out.println("\nStack Debug: ");
        System.out.print("[");
        System.out.println(Application.getCurrentStack().printStack(Application.getCurrentStack()));
        System.out.println("Current Screen: " + Application.getCurrentStack().getScreenCommand());
    }
}