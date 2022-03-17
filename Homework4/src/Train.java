public class Train {
    private int firstCapacity;
    private int secondCapacity;
    private int nextArrival;
    private int trainId = 0;
    private static int numTrains = 4;
    private static Train[] trainArray = new Train[numTrains];

    public static Train[] getTrainArray() {
        return trainArray;
    }

    public void setTrainArray(Train[] trainArray) {
        this.trainArray = trainArray;
    }

    public Train(int trainId) {
        this.trainId = trainId;
    }

    public int getFirstCapacity() { return firstCapacity; }
    public void setFirstCapacity(int firstCapacity) { this.firstCapacity = firstCapacity; }

    public int getSecondCapacity() { return secondCapacity; }
    public void setSecondCapacity(int secondCapacity) { this.secondCapacity = secondCapacity; }

    public int getNextArrival() { return nextArrival; }
    public void setNextArrival(int nextArrival) { this.nextArrival = nextArrival; }

    public void createTrainInstance(){
        for(int i = 0; i < numTrains; i++){
            trainArray[i] = new Train(i);
        }
    }

    public void simulateTimeStep(){

    }
}
