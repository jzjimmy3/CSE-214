//Jimmy Zhang ID: 112844431 CSE 214 R02

import java.util.Scanner;

/**
 * The Interface represents the Command Options.
 * It has classes of Commands for FindPlace, PlanRoute, StartNavigation, GoogleSomething, GotoBookMark,
 * FollowLink, Home, SafariHome, and MapHome
 * The interface has abstractions functions such as validCommand,toString(), toShortString();
 * @author Jimmy Zhang
 */
public interface Command {
    boolean validCommand(CommandStack stack) throws Exception;
    String toString();
    String toShortString();

    /**
     * This class implements the Command Interface and has a constructor that asks for user destination input.
     */
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
        public boolean validCommand(CommandStack stack) throws InvalidCommandException {
            return stack == Application.getMapStack();
        }
        @Override
        public String toString() { return "Showing Results for " + destination; }
        @Override
        public String toShortString() { return "-> F:" + destination; }
    }
    /**
     * This class implements the Command Interface and has a constructor that asks for user source and destination input.
     */
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
        public boolean validCommand(CommandStack stack) throws InvalidCommandException {
            if (stack == Application.getSafariStack()){
                throw new InvalidCommandException("Invalid Command");
            }
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
    /**
     * This class implements the Command Interface and has a constructor starts the navigation route.
     */
    class StartNavigation implements Command {
        private String source;
        private String destination;

        public StartNavigation(){};
        public StartNavigation(CommandStack commandStack) throws Exception {
            if (commandStack == Application.getSafariStack()){
                throw new InvalidCommandException("\nInvalid Command");
            }
            if(commandStack.isEmpty()) {
                throw new CommandStack.EmptyStackException("\nEmpty Stack!");
            }
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
            if(source != null){
                return "Navigating from " + source + " to " + destination;
            } else{
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
    /**
     * This class implements the Command Interface and has a constructor that asks for user query input.
     */
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
        public boolean validCommand(CommandStack stack) throws InvalidCommandException {
            if (stack == Application.getMapStack()){
                throw new InvalidCommandException("Invalid Command");
            }
            return stack == Application.getSafariStack();
        }
        @Override
        public String toString() {
            return "Google: " + query;
        }
        @Override
        public String toShortString() {
            return "-> G:" + query;
        }
    }

    /**
     * This class implements the Command Interface and has a constructor that asks for user favorite bookmark input.
     */

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
        public boolean validCommand(CommandStack stack) throws InvalidCommandException {
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

    /**
     * This class implements the Command Interface and has a constructor that asks for user link input.
     */
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
        public boolean validCommand(CommandStack stack) throws InvalidCommandException, CommandStack.EmptyStackException {
            if (stack == Application.getMapStack()){
                throw new InvalidCommandException("\nInvalid Command");
            }
            if(stack.isEmpty()){
                throw new CommandStack.EmptyStackException("\nEmpty Stack!");
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
            return link;
        }
        @Override
        public String toShortString() {
            return "-> L:" + link;
        }
    }

    /**
     * This class implements the Command Interface and has a constructor that creates the Home Window .
     */

    class Home implements Command{
        public Home(){}
        @Override
        public boolean validCommand(CommandStack stack) {
            return true;
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
    /**
     * This class implements the Command Interface and has a constructor that creates the safariHome Window.
     */
    class safariHome implements Command{
        safariHome(){}
        @Override
        public boolean validCommand(CommandStack stack) {
            return true;
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
    /**
     * This class implements the Command Interface and has a constructor that creates the mapHome Window.
     */
    class mapHome implements Command {
        @Override
        public boolean validCommand(CommandStack stack) {
            return true;
        }

        @Override
        public String toString() {
            return "Maps Home";
        }

        @Override
        public String toShortString() {
            return "-> MapsHome";
        }
    }
    /**
     * This class extends the Exception class and is thrown when the Command is called at the wrong instance.
     */
    class InvalidCommandException extends Exception{
        public InvalidCommandException(String errorMessage){
            super(errorMessage);
        }
    }
}
