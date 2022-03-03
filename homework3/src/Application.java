import java.util.Scanner;

public class Application {
    private static CommandStack mapStack = new CommandStack();
    private static CommandStack safariStack = new CommandStack();
    private static CommandStack currentStack;
    private static String currentScreen = "H";
    public static int countSafari = 0;
    public static int countMap = 0;
    public static Command home = new Command.Home();

    Application(){};

    public void readCommand(Scanner scanner) throws Exception {
        currentStack = stackValue();
        String inputVal = scanner.next().toUpperCase();
        try{
            if(inputVal.matches("[^GFLSBHNP]")){
                throw new Command.InvalidCommandException("\nInvalid Command For This Option Screen\n");
            }
        }catch (Command.InvalidCommandException e){
            System.out.println(e.getMessage());
        }
        switch(inputVal){
            case "G":
                try{
                    if(new Command.GoogleSomething().validCommand(getCurrentStack())){
                        getCurrentStack().push(new Command.GoogleSomething(new Scanner(System.in)));
                    }
                }catch (Command.InvalidCommandException e){
                    System.out.println(e.getMessage());
                }
                break;
            case "F":
                if(new Command.GoToBookmark().validCommand(getCurrentStack())){
                    getCurrentStack().push(new Command.GoToBookmark(new Scanner(System.in)));
                }
                if(new Command.FindPlace().validCommand(getCurrentStack())){
                    getCurrentStack().push(new Command.FindPlace(new Scanner(System.in)));
                }
                break;
            case "L":
                if(new Command.FollowLink().validCommand(getCurrentStack())){
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
            case "N":
                if(new Command.StartNavigation().validCommand(getCurrentStack())){
                    getCurrentStack().push(new Command.StartNavigation(getMapStack()));
                }else{
                    System.out.println("No route or destination!");
                }
                break;
            case "P":
                if(new Command.PlanRoute().validCommand(getCurrentStack())){
                    getCurrentStack().push(new Command.PlanRoute(new Scanner(System.in)));
                }
                break;
            default:break;
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
        if (getCurrentStack() == safariStack) {
            countSafari--;
        } else {
            countMap--;
        }

        while (getMapStack().peek() != home){
            getMapStack().pop();
        }
        while (getSafariStack().peek() != home){
            getSafariStack().pop();
        }
    }
    public void goBack() throws Exception {
        try{
            if(getCurrentStack().isEmpty()){
                throw new CommandStack.EmptyStackException("Empty Stack!");
            }
        }catch(CommandStack.EmptyStackException e){
            System.out.println(e.getMessage());
        }
        Command peekVal = getCurrentStack().peek();
        getCurrentStack().pop();
        if(peekVal instanceof Command.mapHome){
            countMap--;
            iCatchUp.printStack();
            iCatchUp.homeOptions();
        }else if (peekVal instanceof Command.safariHome){
            countSafari--;
            iCatchUp.printStack();
            iCatchUp.homeOptions();
        }else{
            //Empty Command Stack Error
            iCatchUp.printStack();
        }
    }
}