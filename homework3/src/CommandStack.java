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
//        Command c = (Command) stack.peek();
        return "Showing results for.." + ((Command) stack.peek()).toShortString();
    }
    public String toString(){
        return stack.toString();
    }
//    public String toShortString(){
//        return stack.toShortString();
//    }
}
