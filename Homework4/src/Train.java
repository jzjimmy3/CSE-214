public class Train {
    private static int firstCapacity;
    private static int secondCapacity;
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

    public static int getFirstCapacity() { return firstCapacity; }
    public static void setFirstCapacity(int newFirstCapacity) { firstCapacity = newFirstCapacity; }

    public static int getSecondCapacity() { return secondCapacity; }
    public static void setSecondCapacity(int newSecondCapacity) { secondCapacity = newSecondCapacity; }

    public int getNextArrival() { return nextArrival; }
    public void setNextArrival(int nextArrival) { this.nextArrival = nextArrival; }

    public void createTrainInstance(){
        for(int i = 0; i < numTrains; i++){
            trainArray[i] = new Train(i);
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
            int j = Train.getTrainArray().length;
            for(int i = 0; i<Train.getTrainArray().length; i++){
                j--;
                Train.getTrainArray()[i].getFirstCapacity();
            }
        }
    }
}
