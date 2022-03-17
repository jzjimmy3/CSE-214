public class Train {
    private int firstCapacity;
    private int secondCapacity;
    private int nextArrival;
    private int trainId = 0;
    private int numTrains;

    public Train(int trainId) {
        this.trainId = trainId;
    }

    public int getFirstCapacity() { return firstCapacity; }
    public void setFirstCapacity(int firstCapacity) { this.firstCapacity = firstCapacity; }

    public int getSecondCapacity() { return secondCapacity; }
    public void setSecondCapacity(int secondCapacity) { this.secondCapacity = secondCapacity; }

    public int getNextArrival() { return nextArrival; }
    public void setNextArrival(int nextArrival) { this.nextArrival = nextArrival; }

    public Train createTrain(){
        return new Train(++trainId);
    }




    public void simulateTimeStep(){

    }
}
