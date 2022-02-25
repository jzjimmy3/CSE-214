import java.util.Stack;
import java.util.Vector;

public class CommandStack extends Vector {
    public static Stack stack = new Stack();

    public static void push(Command command){
        stack.push(command);
    }
    public Command pop(){
        return (Command) stack.pop();
    }
    public Command peek(){
        return (Command) stack.peek();
    }
    public boolean isEmpty(){
        return stack.isEmpty() ? true:false;
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

//        for(int i = 0; i <= temp.size(); i++){
//            stringStack += ((Command) temp.peek()).toShortString();
//            temp.pop();
//        }
//        stringStack += "]";
//        return stringStack;
        return "";
    }

    public String toString(){
        return stack.toString();
    }
}
