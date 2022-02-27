import java.util.Scanner;

public interface Command {
    boolean validCommand(CommandStack stack);
    String toString();
    String toShortString();

    class FindPlace implements Command {
        private String destination;

        public String getDestination() {
            return destination;
        }

        public FindPlace(Scanner scanner){
            System.out.print("\nPlease enter a location: ");
            this.destination = scanner.nextLine();
        }
        @Override
        public boolean validCommand(CommandStack stack) {
            return Application.getCurrentStack() == Application.getMapStack();
        }

        @Override
        public String toString() {
            return "Showing Results for " + destination;
        }

        @Override
        public String toShortString() {
            return "-> F:" + destination;
        }
    }
    class PlanRoute implements Command{
        private String source;
        private String destination;

        public PlanRoute(Scanner scanner){
            System.out.print("Please enter a source: " );
            String source = scanner.nextLine();
            this.source = source;
            System.out.print("Please enter a destination: " );
            String dest = scanner.nextLine();
            this.destination = dest;
        }

        public String getSource() {
            return source;
        }

        public String getDestination() {
            return destination;
        }

        @Override
        public boolean validCommand(CommandStack stack) {
            if(stack.peek() instanceof StartNavigation ){
                return false;
            }return true;
        }

        @Override
        public String toString() {
            return "Planning route from " + source + " to " + destination;//Showing results for Microsoft Store
        }

        @Override
        public String toShortString() {
            return "-> P: " + source + "-" + destination;
        }
    }
    class StartNavigation implements Command {
        private String source;
        private String destination;
        StartNavigation(){};

        public StartNavigation(CommandStack commandStack) {
            if (!commandStack.isEmpty()) {
                if (commandStack.peek() instanceof PlanRoute) {
                    source = ((PlanRoute) commandStack.peek()).getSource();
                    destination = ((PlanRoute) commandStack.peek()).getDestination();
                }else if ( commandStack.peek() instanceof FindPlace){
                    destination = ((FindPlace) commandStack.peek()).getDestination();
                }
            }
        }

        @Override
        public boolean validCommand(CommandStack stack) {
            System.out.println("Hello1");
            if(stack.isEmpty()){
                System.out.println("Hello2");
                return false;
            }else if(stack.peek() instanceof PlanRoute){
                System.out.println("Hello3");
                return true;
            }
            else if(stack.peek() instanceof FindPlace){
                System.out.println("Hello4");
                return true;
            }else{
                System.out.println("Hello5");
                return false;
            }
        }

        @Override
        public String toString() {
            if(Application.getCurrentStack().peek() instanceof PlanRoute){
                return "Navigating from " + source + " to " + destination;
            }else{
                return "Navigating to " + destination;
            }
        }

        @Override
        public String toShortString() {
            if(Application.getCurrentStack().peek() instanceof PlanRoute) {
                return "-> N: " + source + "-" + destination;
            }else{
                return "-> N: " + destination;
            }
        }
    }

    class GoogleSomething implements Command {
        private String query;
        public GoogleSomething(Scanner scanner){
            System.out.print("Please enter a query: ");
            String input = scanner.nextLine();
            this.query = input;
        }
        @Override
        public boolean validCommand(CommandStack stack) {
            return false;
        }

        @Override
        public String toString() {
            return "Showing Results for " + query;
        }

        @Override
        public String toShortString() {
            return "-> G:" + query;
        }
    }
    class GoToBookmark implements Command{
        private String bookmark;
        public GoToBookmark(Scanner scanner){
            System.out.print("Please enter a bookmark: ");
            String input = scanner.nextLine();
            this.bookmark = input;
        }
        @Override
        public boolean validCommand(CommandStack stack) {
            return false;
        }

        @Override
        public String toString() {
            return "Showing Results for " + bookmark;
        }

        @Override
        public String toShortString() {
            return "-> F:" + bookmark;
        }
    }
    class FollowLink implements Command{
        private String link;
        public FollowLink(Scanner scanner){
            System.out.print("Please enter a link: ");
            String input = scanner.nextLine();
            this.link = input;
        }
        @Override
        public boolean validCommand(CommandStack stack) {
            return false;
        }

        @Override
        public String toString() {
            return "Showing Results for " + link;
        }

        @Override
        public String toShortString() {
            return "-> L:" + link;
        }
    }

    class Home implements Command{
        Home(){}
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
    }
    class safariHome implements Command{
        safariHome(){}
        @Override
        public boolean validCommand(CommandStack stack) {
            return false;
        }
        @Override
        public String toString(){
            return "Safari Home " ;
        }

        @Override
        public String toShortString() {
            return "-> SafariHome";
        }
    }
    class mapHome implements Command {
        @Override
        public boolean validCommand(CommandStack stack) {
            return false;
        }

        @Override
        public String toString() {
            return "Maps Home";//Showing results for Microsoft Store
        }

        @Override
        public String toShortString() {
            return "-> MapsHome";
        }
    };

}
