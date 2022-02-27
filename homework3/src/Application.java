import java.util.Scanner;

public class Application {
    private static CommandStack mapStack = new CommandStack();
    private static CommandStack safariStack = new CommandStack();
    private static CommandStack currentStack;
    private static String currentScreen = "H";
    public static int countSafari = 0;
    public static int countMap = 0;
    public static Command startNav = new Command.StartNavigation();
    public static Command home = new Command.Home();
    public static Command followLink = new Command.FollowLink();


    Application(){};

    public void readCommand(Scanner scanner){
        currentStack = stackValue();
        if(getCurrentScreen() == "S"){
            switch(scanner.next()){
                case "G": getCurrentStack().push(new Command.GoogleSomething(new Scanner(System.in)));
                    break;
                case "F": getCurrentStack().push(new Command.GoToBookmark(new Scanner(System.in)));
                    break;
                case "L":
                    if(followLink.validCommand(getCurrentStack())){
                        getCurrentStack().push(new Command.FollowLink(new Scanner(System.in)));
                    }else{
                        System.out.println("No Link to follow");
                    }
                    break;
                case "S":
                    setCurrentScreen(getCurrentScreen() == "S" ? "M": "S");
                    break;
                case "B": goBack();
                    break;
                case "H"  : goHome();
                    break;
                default:break;
            }
        }else{
            switch(scanner.next()){
                case "F": getCurrentStack().push(new Command.FindPlace(new Scanner(System.in)));
                    break;
                case "P": getCurrentStack().push(new Command.PlanRoute(new Scanner(System.in)));
                    break;
                case "N":
                    if(startNav.validCommand(getCurrentStack())){
                        getCurrentStack().push(new Command.StartNavigation(getMapStack()));
                    }else{
                        System.out.println("No route or destination!");
                    }
                    break;
                case "H"  : goHome();
                    break;
                case "S": setCurrentScreen(getCurrentScreen() == "S" ? "M": "S");
                    break;
                case "B": goBack();
                    break;
                default:break;
            }
        }
    }

    public static CommandStack getMapStack() {
        return mapStack;
    }

    public static void setMapStack(CommandStack mapStack) {
        Application.mapStack = mapStack;
    }

    public static CommandStack getSafariStack() {
        return safariStack;
    }

    public static void setSafariStack(CommandStack safariStack) {
        Application.safariStack = safariStack;
    }

    public static CommandStack getCurrentStack() {
        return currentStack;
    }

    public static void setCurrentStack(CommandStack currentStack) {
        Application.currentStack = currentStack;
    }

    public static String getCurrentScreen() {
        return currentScreen;
    }

    public static void setCurrentScreen(String currentScreen) {
        Application.currentScreen = currentScreen;
    }

    public static int getCountSafari() {
        return countSafari;
    }

    public static void setCountSafari(int countSafari) {
        Application.countSafari = countSafari;
    }

    public static int getCountMap() {
        return countMap;
    }

    public static void setCountMap(int countMap) {
        Application.countMap = countMap;
    }

    public static Command getStartNav() {
        return startNav;
    }

    public static void setStartNav(Command startNav) {
        Application.startNav = startNav;
    }

    public static Command getHome() {
        return home;
    }

    public static void setHome(Command home) {
        Application.home = home;
    }

    public CommandStack stackValue(){
        Command safariHome = new Command.safariHome();
        Command mapHome = new Command.mapHome();
        switch (getCurrentScreen()){
            case "S":
                if(countSafari == 0){
                    getSafariStack().push(safariHome);
                    countSafari++;
                }
                return getSafariStack();
            case "M":
                if(countMap == 0){
                    getMapStack().push(mapHome);
                    countMap++;
                }
                return getMapStack();
            default: break;
        }
        return null;
    }

    public void goHome(){
        currentScreen = "H";
        countSafari--;
        countMap--;
        while (getMapStack().peek() != home){
            getMapStack().pop();
        }
        while (getSafariStack().peek() != home){
            getSafariStack().pop();
        }
    }
    public void goBack() {
        getCurrentStack().pop();
    }
}