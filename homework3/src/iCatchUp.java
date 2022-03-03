//Jimmy Zhang ID: 112844431 CSE 214 R02

import java.util.Scanner;

/**
 * This class is the driver class for Command, CommandStack, and Application
 */
public class iCatchUp {
    public static boolean quitValue = true;
    public static Scanner input = new Scanner(System.in);
    public static Application apps = new Application();

    public static void main(String[] args) throws Exception {
        System.out.println("\nWelcome to the iPhony pocket telegraph simulator.\nYou are on the home screen.\n");
        Application.getMapStack().push(new Command.Home());
        Application.getSafariStack().push(new Command.Home());
        while(quitValue){
            currentScreen();
        }
    }

    /**
     * This method prints the home option and accurately selects the next page
     * @throws Exception
     */
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
                Application.setCurrentScreen("H");
                throw new Command.InvalidCommandException("\nInvalid Command for Home Options\n");
            }
        }catch (Command.InvalidCommandException e){
            System.out.println(e.getMessage());
        }
        switch (inputVal) {
            case "S":
                Application.setCurrentScreen("S");
                Application.setCurrentStack(apps.stackValue());
                Application.printStack();
                safariOptions();
                break;
            case "M":
                Application.setCurrentScreen("M");
                Application.setCurrentStack(apps.stackValue());
                Application.printStack();
                mapOptions();
                break;
            case "Q":
                quitValue = false;
                System.out.println("Sorry to see you go, tell the iPod I said hi!");
                break;
        }
    }

    /**
     * This option is the mapOptions and accurately selects the Commands associated with it.
     * @throws Exception
     */
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
    }
    /**
     * This option is the safariOptions and accurately selects the Commands associated with it.
     * @throws Exception
     */
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
    }

    /**
     * This method associates the current screen with a variable
     * @throws Exception
     */
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
}