public class Passenger {
    private int id, arrivalTime;
    private boolean isFirstClass;
    public Passenger(int id, int arrivalTime, boolean isFirstClass) {
        this.id = id;
        this.arrivalTime = arrivalTime;
        this.isFirstClass = isFirstClass;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getArrivalTime() { return arrivalTime; }
    public void setArrivalTime(int arrivalTime) { this.arrivalTime = arrivalTime; }

    public boolean isFirstClass() { return isFirstClass; }
    public void setFirstClass(boolean firstClass) { isFirstClass = firstClass; }
}
