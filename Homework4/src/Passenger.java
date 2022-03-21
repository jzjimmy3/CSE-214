//Jimmy Zhang ID: 112844431 CSE 214 RO2

/**
 * This class is the Passenger Class, there is a constructor that takes in an int id, arrivalTime, and boolean isFirstClass
 * @author Jimmy Zhang
 */
public class Passenger {
    private int id, arrivalTime;
    private boolean isFirstClass;
    public Passenger(int id, int arrivalTime, boolean isFirstClass) {
        this.id = id;
        this.arrivalTime = arrivalTime;
        this.isFirstClass = isFirstClass;
    }
    /**
     * The code below represents the getters and setter methods for this class.
     * @author Jimmy Zhang
     */

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getArrivalTime() { return arrivalTime; }
    public void setArrivalTime(int arrivalTime) { this.arrivalTime = arrivalTime; }

    public boolean isFirstClass() { return isFirstClass; }
    public void setFirstClass(boolean firstClass) { isFirstClass = firstClass; }

    @Override
    public String toString() {
        return "P" + id;
    }
}
