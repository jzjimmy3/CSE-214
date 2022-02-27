import java.util.Stack;
import java.util.Vector;

public class CommandStack extends Vector {
    private Stack<Command> stack = new Stack<>();

    public void push(Command command){
        stack.push(command);
    }
    public Command pop(){
        return stack.pop();
    }
    public Command peek(){
        return stack.peek();
    }
    public boolean isEmpty(){
        return stack.isEmpty();
    }
    public String getScreenCommand(){
        return !stack.isEmpty()? stack.peek().toString() : "Empty Stack";
    }

    public String printStack(CommandStack stacks) {
        if (stacks.isEmpty()) return "Empty Stack";
        Command commands = stacks.peek();
        stacks.pop();
        printStack(stacks);
        System.out.print(commands.toShortString() + " ");
        stacks.push(commands);
        return "";
    }
    public String toString(){
        return stack.toString();
    }
}
