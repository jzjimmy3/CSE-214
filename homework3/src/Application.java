//Jimmy Zhang ID: 112844431 CSE 214 R02

import java.util.Scanner;

/**
 * This class is the application class and has fields of mapStack, safariStack, currentStack.
 * @author Jimmy
 */
public class Application {
    private static CommandStack mapStack = new CommandStack();
    private static CommandStack safariStack = new CommandStack();
    private static CommandStack currentStack;
    private static String currentScreen = "H";
    public static int countSafari = 0;
    public static int countMap = 0;

    Application(){};

    /**
     * @param scanner
     * @throws Exception
     * This method reads the command inputted and determines whether it is valid or not.
     */
    public void readCommand(Scanner scanner) throws Exception {
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
                setCurrentStack(stackValue());
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
        printStack();
    }

    /**
     * @return The methods listed below are getter and setter functions for the fields
     */
    public static CommandStack getMapStack() { return mapStack; }
    public static void setMapStack(CommandStack newMapStack) { mapStack = newMapStack; }

    public static CommandStack getSafariStack() { return safariStack; }
    public static void setSafariStack(CommandStack newSafariStack) { safariStack = newSafariStack; }

    public static CommandStack getCurrentStack() { return currentStack; }
    public static void setCurrentStack(CommandStack newCurrentStack) { currentStack = newCurrentStack; }

    public static String getCurrentScreen() { return currentScreen; }
    public static void setCurrentScreen(String newCurrentScreen) { currentScreen = newCurrentScreen; }

    public static int getCountSafari() { return countSafari; }
    public static void setCountSafari(int newCountSafari) { countSafari = newCountSafari; }

    public static int getCountMap() { return countMap; }
    public static void setCountMap(int newCountMap) { countMap = newCountMap; }

    /**
     * @return This function returns the value of the stack depending on the current screen
     */
    public CommandStack stackValue(){
        switch (getCurrentScreen()){
            case "S":
                if(countSafari == 0){
                    getSafariStack().push(new Command.safariHome());
                    countSafari++;
                }
                return getSafariStack();
            case "M":
                if(countMap == 0){
                    getMapStack().push(new Command.mapHome());
                    countMap++;
                }
                return getMapStack();
            default: break;
        }
        return null;
    }

    /**
     * This function removes every element in the stack and return to the home page
     */
    public void goHome(){
        currentScreen = "H";
        if (getCurrentStack() == safariStack) {
            countSafari--;
        } else {
            countMap--;
        }
        while (!(getMapStack().peek() instanceof Command.Home)){
            getMapStack().pop();
        }
        while (!(getMapStack().peek() instanceof Command.Home)){
            getSafariStack().pop();
        }
    }

    /**
     * @throws Exception This function returns the previous state of the stack
     */
    public void goBack() throws Exception {
        Command peekVal = getCurrentStack().peek();
        if(!(peekVal instanceof Command.Home)) getCurrentStack().pop();

        if(peekVal instanceof Command.mapHome){
            countMap--;
            iCatchUp.homeOptions();
        }else if (peekVal instanceof Command.safariHome){
            countSafari--;
            iCatchUp.homeOptions();
        }else if (peekVal instanceof Command.Home){
            iCatchUp.homeOptions();
        }
        try{
            if(peekVal instanceof Command.Home){
                throw new CommandStack.EmptyStackException("Empty Stack!");
            }
        }catch(CommandStack.EmptyStackException e){
            System.out.println(e.getMessage());
        }
    }
    /**
     * This method formats the stack and prints it respectively.
     */
    public static void printStack(){
        System.out.println("\nStack Debug: ");
        System.out.print("[");
        System.out.println(Application.getCurrentStack().printStack(Application.getCurrentStack()));
        System.out.println("Current Screen: " + Application.getCurrentStack().getScreenCommand());
    }
}