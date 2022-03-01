import java.util.Scanner;

public interface Command {
    boolean validCommand(CommandStack stack);
    String toString();
    String toShortString();

    class FindPlace implements Command {
        private String destination;

        public FindPlace(){}
        public FindPlace(Scanner scanner){
            System.out.print("\nPlease enter a location: ");
            this.destination = scanner.nextLine();
        }

        public String getDestination() { return destination; }
        public void setDestination(String destination) { this.destination = destination; }

        @Override
        public boolean validCommand(CommandStack stack) {
            return stack == Application.getMapStack();
        }
        @Override
        public String toString() { return "Showing Results for " + destination; }
        @Override
        public String toShortString() { return "-> F:" + destination; }
    }

    class PlanRoute implements Command{
        private String source;
        private String destination;

        public PlanRoute(){}
        public PlanRoute(Scanner scanner){
            System.out.print("Please enter a source: " );
            this.source = scanner.nextLine();
            System.out.print("Please enter a destination: " );
            this.destination = scanner.nextLine();
        }

        public String getSource() { return source; }
        public void setSource(String source) { this.source = source; }
        public String getDestination() { return destination; }
        public void setDestination(String destination) { this.destination = destination; }

        @Override
        public boolean validCommand(CommandStack stack) {
            return stack == Application.getMapStack();
        }
        @Override
        public String toString() {
            return "Planning route from " + source + " to " + destination;
        }
        @Override
        public String toShortString() {
            return "-> P: " + source + "-" + destination;
        }
    }
    class StartNavigation implements Command {
        private String source;
        private String destination;

        public StartNavigation(){};
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

        public String getSource() { return source; }
        public void setSource(String source) { this.source = source; }
        public String getDestination() { return destination; }
        public void setDestination(String destination) { this.destination = destination; }

        @Override
        public boolean validCommand(CommandStack stack) {
            if(stack.isEmpty()){
                return false;
            }else if(stack.peek() instanceof PlanRoute){
                return true;
            }
            else if(stack.peek() instanceof FindPlace){
                return true;
            }else{
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

        public GoogleSomething(){}
        public GoogleSomething(Scanner scanner){
            System.out.print("Please enter a query: ");
            String input = scanner.nextLine();
            this.query = input;
        }

        public String getQuery() { return query; }
        public void setQuery(String query) { this.query = query; }

        @Override
        public boolean validCommand(CommandStack stack) {
            return stack == Application.getSafariStack();
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

        public GoToBookmark(){}
        public GoToBookmark(Scanner scanner){
            System.out.print("Please enter a bookmark: ");
            this.bookmark = scanner.nextLine();
        }

        public String getBookmark() { return bookmark; }
        public void setBookmark(String bookmark) { this.bookmark = bookmark; }

        @Override
        public boolean validCommand(CommandStack stack) {
            return stack == Application.getSafariStack();
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

        public FollowLink(){}
        public FollowLink(Scanner scanner){
            System.out.print("Please enter a link: ");
            this.link = scanner.nextLine();
        }

        public String getLink() { return link; }
        public void setLink(String link) { this.link = link; }

        @Override
        public boolean validCommand(CommandStack stack) {
            if(stack.isEmpty()){
                return false;
            }else if(stack.peek() instanceof GoogleSomething){
                return true;
            }
            else if(stack.peek() instanceof GoToBookmark){
                return true;
            }else{
                return false;
            }
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
        public Home(){}
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
            return "Maps Home";
        }

        @Override
        public String toShortString() {
            return "-> MapsHome";
        }
    };

}
