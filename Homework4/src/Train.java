public class Train {
    private static int firstCapacity;
    private static int secondCapacity;
    private int nextArrival;
    private int trainId;
    private int stationId;
    private static int numTrains = 4;
    private static Train[] trainArray = new Train[numTrains];

    public static Train[] getTrainArray() {
        return trainArray;
    }

    public void setTrainArray(Train[] trainArray) {
        this.trainArray = trainArray;
    }

    public Train(int trainId, int stationId) {
        this.trainId = trainId;
        this.stationId = stationId;
    }

    public int getTrainId() {
        return trainId;
    }

    public void setTrainId(int trainId) {
        this.trainId = trainId;
    }

    public static int getFirstCapacity() { return firstCapacity; }
    public static void setFirstCapacity(int newFirstCapacity) { firstCapacity = newFirstCapacity; }

    public static int getSecondCapacity() { return secondCapacity; }
    public static void setSecondCapacity(int newSecondCapacity) { secondCapacity = newSecondCapacity; }

    public int getNextArrival() { return nextArrival; }
    public void setNextArrival(int nextArrival) { this.nextArrival = nextArrival; }

    public int getStationId() {
        return stationId;
    }

    public void setStationId(int stationId) {
        this.stationId = stationId;
    }

    public static void createTrainInstance(){
        for(int i = 0; i < numTrains; i++){
            trainArray[i] = new Train(i,0);
        }
    }

    public void simulateTimeStep(){
        Station station = new Station();
//      Number of Stops: the last train is going to take (number of stations + number of trains - 1) stops to reach the end
//      AKA for Time:  it's Number of Stops * 5 min
//        int timeForLastTrainToReachEnd = 4 + numTrains -1;
//
//        if(station.getCurrentMin() % 5  == 0){
//
//        }

        int trainStation = 0; // 1 means train is at mineola, 2 means hick's, 3 means syosset, 4 means huntington

        if(station.getCurrentMin() % 5 == 0){
            trainStation++;
            for(int i = 0; i<Train.getTrainArray().length; i++){
                Train.getTrainArray()[i].getFirstCapacity();
            }
        }
    }
}
