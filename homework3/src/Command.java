import java.util.Scanner;

public interface Command {
    boolean validCommand(CommandStack stack);
    String toString();
    String toShortString();

    class FindPlace implements Command {
        private String destination;
        public FindPlace(Scanner scanner){
            System.out.print("Please enter a place: ");
            String input = scanner.nextLine();
            this.destination = input;
        }
        @Override
        public boolean validCommand(CommandStack stack) {
            return false;
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
            return false;
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

        public StartNavigation(CommandStack commandStack) {
            if (!commandStack.isEmpty()) {
                if (commandStack.peek() instanceof PlanRoute) {
                    source = ((PlanRoute) commandStack.peek()).getSource();
                    destination = ((PlanRoute) commandStack.peek()).getDestination();
                }
            }
        }

        @Override
        public boolean validCommand(CommandStack stack) {
            return false;
        }

        @Override
        public String toString() {
            // toString is supposed to be for Current Screen
            return "Navigating from " + source + " to " + destination;//Showing results for Microsoft Store
        }

        @Override
        public String toShortString() {
            //toShortString is supposed to be for Stack
            return "-> N: " + source + "-" + destination;
        }
    }
    class GoogleSomething implements Command{
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
            return "-> F:" + link;
        }
    }

}
