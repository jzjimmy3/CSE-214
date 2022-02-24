public interface Command {
    boolean validCommand(CommandStack stack);
    String toString();
    String toShortString();
}
