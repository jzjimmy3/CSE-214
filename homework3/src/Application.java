import java.util.Scanner;

public class Application {
    private static CommandStack mapStack = new CommandStack();
    private static CommandStack safariStack = new CommandStack();
    private static CommandStack currentStack;

    private static String currentScreen = "H";
    Application(){};
    public void readCommand(Scanner scanner){
        currentStack = stackValue();
        if(getCurrentScreen() == "S"){
            switch(scanner.next()){
                case "G": getCurrentStack().push(new Command.GoogleSomething(new Scanner(System.in)));
                    break;
                case "F": getCurrentStack().push(new Command.GoToBookmark(new Scanner(System.in)));
                    break;
                case "L": getCurrentStack().push(new Command.FollowLink(new Scanner(System.in)));
                    break;
                case "S":
                    if(getCurrentScreen() == "S"){
                        setCurrentScreen("M");
                    }else{
                        setCurrentScreen("S");
                    }
                    break;
                default:break;
            }
        }else{
            switch(scanner.next()){
                case "F": getCurrentStack().push(new Command.FindPlace(new Scanner(System.in)));
                    break;
                case "P": getCurrentStack().push(new Command.PlanRoute(new Scanner(System.in)));
                    break;
                case "N": getCurrentStack().push(new Command.StartNavigation(getMapStack()));
                    break;
                case "H"  : getCurrentStack().pop();
                    break;
                case "S":
                    if(getCurrentScreen() == "S"){
                        setCurrentScreen("M");
                    }else{
                        setCurrentScreen("S");
                    }
                    break;
                case "B": getMapStack().pop();
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

    public void goBack() {//return the application to state it was before most recent command
        }
    public CommandStack stackValue(){
        Command safariHome = new Command.safariHome();
        Command mapHome = new Command.mapHome();
        switch (getCurrentScreen()){
            case "S":
                if(iCatchUp.getCountSafari() == 0){
                    getSafariStack().push(safariHome);
                    iCatchUp.setCountSafari(iCatchUp.getCountSafari() + 1);

                }
                return getSafariStack();
            case "M":
                if(iCatchUp.getCountMap() == 0){
                    getMapStack().push(mapHome);
                    iCatchUp.setCountMap(iCatchUp.getCountMap() + 1);
                }
                return getMapStack();
            default: break;
        }
        return null;
    }
}