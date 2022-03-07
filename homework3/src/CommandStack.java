//Jimmy Zhang ID: 112844431 CSE 214 R02

import java.util.Stack;
import java.util.Vector;

/**
 * This class creates a stack with a type of Command. By utilizing the Java Stack data structure, we able to call stack methods
 *
 * @author Jimmy Zhang
 */
public class CommandStack extends Vector {
    private Stack<Command> stack = new Stack<>();

    /**
     * @param command adds Command on to the Command Stack
     */
    public void push(Command command){ stack.push(command); }

    /**
     * @return removes Command from top of the Command Stack
     */
    public Command pop(){ return stack.pop(); }

    /**
     * @return gets the value of the Command from the top of the stack
     */
    public Command peek(){ return stack.peek(); }

    /**
     * @return Checks to see if CommandStack is empty
     */
    public boolean isEmpty(){ return stack.isEmpty(); }

    /**
     * @return gets the value of Screen Command
     */
    public String getScreenCommand(){ return !stack.isEmpty()? stack.peek().toString() : "Empty Stack"; }
        /**
         * @param stacks Prints out the CommandStack
         * @return
         */
    public String printStack(CommandStack stacks) {
        if (stacks.isEmpty()) return "Empty Stack";
        Command commands = stacks.peek();
        stacks.pop();
        printStack(stacks);
        System.out.print(commands.toShortString() + " ");
        stacks.push(commands);
        return "";
    }
    public String toString(){ return stack.peek().toString(); }

    /**
     * A class that extends exception and is thrown whenever there is an empty stack in CommandStack.
     */
    public static class EmptyStackException extends Exception{
        public EmptyStackException(String errorMessage){
            super(errorMessage);
        }
    }
}
