import java.util.Scanner;

public class iCatchUp {
    public static boolean quitValue = true;
    public static Scanner input = new Scanner(System.in);
    public static CommandStack stackDebug = new CommandStack();

    public static void main(String[] args) {
//        System.out.println("Stack debug.toString1: " + stackDebug.getScreenCommand());

        while(quitValue){
            homeOptions();
//            for(int i = 0; i<stackDebug..length;i++){
//
//            }
            System.out.println("Stack Debug: " + stackDebug.getStack());
            System.out.println("Current Screen: " + stackDebug.getScreenCommand());

        }
    }

    public static void homeOptions(){
        //[Home]
        System.out.println("Home Options: \n" +
                "   S) Safari\n" +
                "   M) Maps\n" +
                "   Q) Quit\n"
        );
        System.out.print("Please select an option: ");
        switch (input.next().toUpperCase()){
            case "S": safariOptions();
                    break;
            case "M": mapOptions();
                    break;
            case "Q": quitValue = false;
                    break;
            default:break;
        }
    }
    public static void mapOptions(){
        System.out.println("Map Options: \n" +
                "   F) Find a place\n" +
                "   P) Plan a route\n" +
                "   N) StartNavigation\n" +
                "   H) Home Screen\n" +
                "   S) Switch to Safari\n" +
                "   B) Back\n"
        );
        System.out.print("Please select an option: ");
        switch (input.next().toUpperCase()){
            case "F": findPlace();
                break;
            case "P": planRoute();
                break;
            case "N": ;
                break;
            case "H": ;
                break;
            case "S": ;
                break;
            case "B": ;
                break;
            default:break;
        }
    }
    public static void safariOptions(){
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
            case "G": ;
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
    }
    public static void findPlace(){
        System.out.print("Please enter a place: ");
        FindPlace findPlace = new FindPlace(new Scanner(System.in));
        stackDebug.push(findPlace);
    }
    public static void planRoute(){

    }
}
