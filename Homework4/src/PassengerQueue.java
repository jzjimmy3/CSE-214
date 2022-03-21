//Jimmy Zhang ID: 112844431 CSE 214 RO2

import java.util.AbstractSequentialList;
import java.util.ArrayList;
import java.util.ListIterator;

/**
 * This class is the PassengerQueue Class, when passengers arrive at the station, they will be placed in a passengerQueue
 * The PassengerQueue extends from AbstractSequentialList and utilizes an ArrayList.
 * @author Jimmy Zhang
 */

public class PassengerQueue extends AbstractSequentialList {
    public ArrayList list = new ArrayList<Passenger>();

    /**
     * The enqueue function takes in a Passenger parameter and adds it to the rear of the line.
     * @param p
     */
    public void enqueue(Passenger p){
        list.add(p);
    }
    /**
     * The dequeue function takes removes a passenger from the front of the line.
     */
    public void dequeue(){
        list.remove(0);
    }

    /**
     * The toString function prints out the passengerQueue
     */
    public String toString(){
        return list.toString();
    }
    /**
     * The peek function looks at the front of the line and returns it
     */
    public Passenger peek(){
        return (Passenger) list.get(0);
    }

    @Override
    public int size() {
        return list.size();
    }

    /**
     * The isEmpty function determines if the queue is empty
     */
    public boolean isEmpty(){
        return list.isEmpty();
    }

    @Override
    public ListIterator listIterator(int index) {
        return null;
    }
}
