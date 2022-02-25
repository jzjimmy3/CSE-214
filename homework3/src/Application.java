import java.util.Scanner;

public class Application {
    private static CommandStack mapStack = new CommandStack();
    private static CommandStack safariStack = new CommandStack();
    private static CommandStack stack;
    private static String currentScreen = "H";
    Application(){};
    public void readCommand(Scanner scanner){
        if(getCurrentScreen() == "S"){
            switch(scanner.next()){
                case "G": getStack().push(new Command.GoogleSomething(new Scanner(System.in)));
                    break;
                case "F": getStack().push(new Command.GoToBookmark(new Scanner(System.in)));
                    break;
                case "L": getStack().push(new Command.FollowLink(new Scanner(System.in)));
                    break;
                case "H": getStack().pop();
                    break;
                case "S":
                    setStack(getStack() == getSafariStack()? getMapStack():getSafariStack());
                    if(getCurrentScreen() == "S"){
                        setCurrentScreen("M");
                    }else{
                        setCurrentScreen("S");
                    }
                    break;
                case "B": System.out.println("Backup");
                    break;
                default:break;
            }
        }else{
            switch(scanner.next()){
                case "F": getStack().push(new Command.FindPlace(new Scanner(System.in)));
                    break;
                case "P": getStack().push(new Command.PlanRoute(new Scanner(System.in)));
                    break;
                case "N": getStack().push(new Command.StartNavigation(getStack()));
                    break;
                case "H": getStack().pop();
                    break;
                case "S":
                    if(getCurrentScreen() == "S"){
                        setCurrentScreen("M");
                    }else{
                        setCurrentScreen("S");
                    }
                    setStack(stackValue());
                    System.out.println("Getsafaristacl: " + getSafariStack());
                    System.out.println("Getmap: " +getMapStack());
                    break;
//                    setStack(stackValue());
                case "B": System.out.println("Backup");
                    break;
                default:break;
            }
        }
    }

    public static CommandStack getMapStack() { return mapStack; }
    public static void setMapStack(CommandStack mapStack) { Application.mapStack = mapStack; }

    public static CommandStack getSafariStack() { return safariStack; }

    public static void setSafariStack(CommandStack safariStack) { Application.safariStack = safariStack; }

    public static CommandStack getStack() { return stack; }

    public static void setStack(CommandStack stack) { Application.stack = stack; }

    public static String getCurrentScreen() { return currentScreen; }

    public static void setCurrentScreen(String currentScreen) { Application.currentScreen = currentScreen; }

    public void goBack() {//return the application to state it was before most recent command
        }
    public CommandStack stackValue(){
        switch (currentScreen){
            case "S": return getSafariStack();
            case "M": return getMapStack();
            default: break;
        }
        return null;
    }
}