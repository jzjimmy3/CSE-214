public class Train {
    private static int firstCapacity;
    private static int secondCapacity;
    private int nextArrival;
    private int trainId;
    private int stationId;
    private int arrivalToFirstStation;
    private static int numTrains = 4;
    private static Train[] trainArray = new Train[numTrains];
    private int availableRoom;

    public static Train[] getTrainArray() {
        return trainArray;
    }

    public void setTrainArray(Train[] trainArray) {
        this.trainArray = trainArray;
    }

    public Train(int trainId, int stationId, int arrivalToFirstStation, int availableRoom) {
        this.trainId = trainId;
        this.stationId = stationId;
        this.arrivalToFirstStation = arrivalToFirstStation;
        this.availableRoom = availableRoom;
    }

    public int getAvailableRoom() {
        return availableRoom;
    }

    public void setAvailableRoom(int availableRoom) {
        this.availableRoom = availableRoom;
    }

    public int getTrainId() {
        return trainId;
    }

    public void setTrainId(int trainId) {
        this.trainId = trainId;
    }

    public int getArrivalToFirstStation() {
        return arrivalToFirstStation;
    }

    public void setArrivalToFirstStation(int arrivalToFirstStation) {
        this.arrivalToFirstStation = arrivalToFirstStation;
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

    public static int getNumTrains() {
        return numTrains;
    }

    public static void setNumTrains(int numTrains) {
        Train.numTrains = numTrains;
    }

    public static void createTrainInstance(){
        for(int i = 0; i < numTrains; i++){
            trainArray[i] = new Train(i,0, i*5, firstCapacity);
        }
    }

    public void simulateTimeStep(){

    }
}
