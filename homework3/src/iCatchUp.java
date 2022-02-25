import java.util.Scanner;

public class iCatchUp {
    public static boolean quitValue = true;
    public static Scanner input = new Scanner(System.in);
    public static CommandStack mapStack = new CommandStack();
    public static CommandStack safariStack = new CommandStack();
    public static CommandStack currentStack;
    public static String currentScreen = "H";
    public static int countHome = 0;
    public static int countSafari = 0;
    public static int countMap = 0;


    public static void main(String[] args) {
        while(quitValue){
            currentScreen();
        }
    }

    public static void homeOptions(){

        Command home = new Command() {
            @Override
            public boolean validCommand(CommandStack stack) {
                return false;
            }
            @Override
            public String toString(){
                return "Home" ;
            }

            @Override
            public String toShortString() {
                return "Home";
            }
        };

        System.out.println("Home Options: \n" +
                "   S) Safari\n" +
                "   M) Maps\n" +
                "   Q) Quit\n"
        );
        System.out.print("Please select an option: ");
        String inputs = input.next().toUpperCase();
        switch (inputs){
            case "S":
                currentScreen = "S";
                currentStack = safariStack;
                if(countHome == 0){
                    currentStack.push(home);
                    countHome++;
                }
                safariOptions();
                printStack();
                    break;
            case "M":
                currentScreen = "M";
                currentStack = mapStack;
                if(countHome == 0){
                    currentStack.push(home);
                    countHome++;
                }
                mapOptions();
                printStack();
                break;
            case "Q": quitValue = false;
                    break;
            default:break;
        }
    }
    public static void mapOptions(){
        Command mapHome = new Command() {
            @Override
            public boolean validCommand(CommandStack stack) {
                return false;
            }
            @Override
            public String toString(){
                return "MapHome: " ;//Showing results for Microsoft Store
            }

            @Override
            public String toShortString() {
                return "-> MapsHome";
            }
        };
        System.out.println("Map Options: \n" +
                "   F) Find a place\n" +
                "   P) Plan a route\n" +
                "   N) Start Navigation\n" +
                "   H) Home Screen\n" +
                "   S) Switch to Safari\n" +
                "   B) Back\n"
        );
        if(countMap == 0){
            currentStack.push(mapHome);
            countMap++;
        }
        printStack();
        System.out.print("Please select an option: ");
        switch (input.next().toUpperCase()){
            case "F": findPlaces();
                break;
            case "P": planRoute();
                break;
            case "N": startNavigation();
                break;
            case "H": homeScreen();
                break;
            case "S": switchView();
                break;
            case "B": back();
                break;
            default:break;
        }
    }
    public static void safariOptions(){
        Command safariHome = new Command() {
            @Override
            public boolean validCommand(CommandStack stack) {
                return false;
            }
            @Override
            public String toString(){
                return "SafariHome: " ;//Showing results for Microsoft Store
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
        System.out.print("Please select an option: ");
        switch (input.next().toUpperCase()){
            case "G": googleSomething();
                break;
            case "F": ;
                break;
            case "L": ;
                break;
            case "H": ;
                break;
            case "S": ;
                break;
            case "B": ;
                break;
            default:break;
        }
        if(countSafari == 0){
            currentStack.push(safariHome);
            countSafari++;
        }
        printStack();

    }

    public static void currentScreen(){
        switch(currentScreen){
            case "H": homeOptions();
                break;
            case "S": safariOptions();
                break;
            case "M": mapOptions();
                break;
            default:break;
        }
    }

    public static void findPlaces(){
        Command.FindPlace findPlace = new Command.FindPlace(new Scanner(System.in));
        currentStack.push(findPlace);
    }
    public static void planRoute(){
        Command.PlanRoute planRoute = new Command.PlanRoute(new Scanner(System.in));
        currentStack.push(planRoute);
    }
    public static void startNavigation(){
        Command.StartNavigation startNavigation= new Command.StartNavigation(currentStack);
        currentStack.push(startNavigation);
    }
    public static void homeScreen(){
        currentStack.pop();
        currentScreen = "H";
    }
    public static void switchView(){
        currentStack = currentStack == safariStack? mapStack:safariStack;
        currentScreen = currentScreen == "H"? "S": "H";
    }
    public static void back(){
        System.out.println("Backup");
//        stackDebug.pop();
    }

    public static void googleSomething(){
        Command.GoogleSomething googleSomething = new Command.GoogleSomething(new Scanner(System.in));
        currentStack.push(googleSomething);
    }

    public static void printStack(){
        System.out.print("Stack Debug: [");
        System.out.println(currentStack.printStack(currentStack));
        System.out.println("Current Screen: " + currentStack.getScreenCommand());
    }

}
