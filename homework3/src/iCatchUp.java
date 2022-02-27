import java.util.Scanner;

public class iCatchUp {
    public static boolean quitValue = true;
    public static Scanner input = new Scanner(System.in);
    public static int countHome = 0;
    public static int countSafari = 0;
    public static int countMap = 0;
    public static Application apps = new Application();
    public static Command home = new Command.Home();

    public static int getCountHome() {
        return countHome;
    }

    public static void setCountHome(int countHome) {
        iCatchUp.countHome = countHome;
    }

    public static int getCountSafari() {
        return countSafari;
    }

    public static void setCountSafari(int countSafari) {
        iCatchUp.countSafari = countSafari;
    }

    public static int getCountMap() {
        return countMap;
    }

    public static void setCountMap(int countMap) {
        iCatchUp.countMap = countMap;
    }

    public static void main(String[] args) {
        System.out.println("\nWelcome to the iPhony pocket telegraph simulator.\nYou are on the home screen.\n");
        Application.getMapStack().push(home);
        Application.getSafariStack().push(home);
        while(quitValue){
            currentScreen();
        }
    }

    public static void homeOptions(){
        System.out.println("Home Options: \n" +
                "   S) Safari\n" +
                "   M) Maps\n" +
                "   Q) Quit\n"
        );
        System.out.print("Please select an option: ");
        switch (input.next().toUpperCase()){
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
            case "Q": quitValue = false;
                System.out.println("Sorry to see you go, tell the iPod I said hi!");
                    break;
            default:break;
        }
    }
    public static void mapOptions() {
        Command mapHome = new Command.mapHome();
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
    public static void safariOptions(){
        Command safariHome = new Command.safariHome();
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

    public static void currentScreen(){
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
