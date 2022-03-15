import java.util.AbstractSequentialList;
import java.util.ArrayList;
import java.util.ListIterator;

public class PassengerQueue extends AbstractSequentialList {
    public ArrayList list = new ArrayList<Passenger>();
    public void enqueue(Passenger p){
        list.add(p);
    }
    public void dequeue(){
        list.remove(0);
    }
    public String toString(){
        return list.toString();
    }
    public Passenger peek(){
        return (Passenger) list.get(4);
    }

    @Override
    public int size() {
        return 0;
    }

    public boolean isEmpty(){
        return list.isEmpty();
    }

    @Override
    public ListIterator listIterator(int index) {
        return null;
    }
}
