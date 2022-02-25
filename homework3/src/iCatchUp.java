import java.util.Scanner;

public class iCatchUp {
    public static boolean quitValue = true;
    public static Scanner input = new Scanner(System.in);
    public static int countHome = 0;
    public static int countSafari = 0;
    public static int countMap = 0;
    public static Application apps = new Application();

    public static void main(String[] args) {
        while(quitValue){
            currentScreen();
        }
    }

    public static void homeOptions(){
        Command home = new Command.Home();
        System.out.println("\nWelcome to the iPhony pocket telegraph simulator. You are on the home screen.\n");
        System.out.println("Home Options: \n" +
                "   S) Safari\n" +
                "   M) Maps\n" +
                "   Q) Quit\n"
        );
        System.out.print("Please select an option: ");
        String inputs = input.next().toUpperCase();
        switch (inputs){
            case "S":
                Application.setCurrentScreen("S");
                Application.setStack(Application.getSafariStack());
                if(countHome == 0){
                    apps.getStack().push(home);
                    countHome++;
                }
                printStack();
                safariOptions();
                    break;
            case "M":
                Application.setCurrentScreen("M");
                Application.setStack(Application.getMapStack());
                if(countHome == 0){
                    CommandStack.push(home);
                    countHome++;
                }
                mapOptions();
                break;
            case "Q": quitValue = false;
                System.out.println("Sorry to see you go, tell the iPod I said hi!");
                    break;
            default:break;
        }
    }
    public static void mapOptions() {
        Application.setCurrentScreen("M");
        Command mapHome = new Command() {
            @Override
            public boolean validCommand(CommandStack stack) {
                return false;
            }

            @Override
            public String toString() {
                return "MapHome: ";//Showing results for Microsoft Store
            }

            @Override
            public String toShortString() {
                return "-> MapsHome";
            }
        };
        if (countMap == 0) {
            Application.getStack().push(mapHome);
            countMap++;
        }
        printStack();
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
        Application.setCurrentScreen("S");
        Command safariHome = new Command() {
            @Override
            public boolean validCommand(CommandStack stack) {
                return false;
            }
            @Override
            public String toString(){
                return "SafariHome: " ;
            }

            @Override
            public String toShortString() {
                return "-> SafariHome";
            }
        };
        System.out.println(
                "Safari Options: \n" +
                "   G) Google Something\n" +
                "   F) Go to a favorite (bookmark)\n" +
                "   L) Follow a link\n" +
                "   H) Home Screen\n" +
                "   S) Switch to Maps\n" +
                "   B) Back\n"
        );
        if(countSafari == 0){
            apps.getStack().push(safariHome);
            countSafari++;
        }
        System.out.print("Please select an option: ");
        apps.readCommand(new Scanner(System.in));
        printStack();
    }

    public static void currentScreen(){
        System.out.println("The value of current screen is: " + apps.getCurrentScreen());
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

    public static void back(){
        System.out.println("Backup");
//        stackDebug.pop();
    }

    public static void printStack(){
        System.out.print("Stack Debug: [");
        System.out.println(apps.getStack().printStack(apps.getStack()));
        System.out.println("Current Screen: " + apps.getStack().getScreenCommand());
    }

}
