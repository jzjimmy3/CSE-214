//Jimmy Zhang ID: 112844431 CSE 214 RO2

import java.util.ArrayList;

/**
 * The Train class represents a Train which has fields such as first capacity, second capacity, and nextArrival.
 * @author Jimmy Zhang
 */
public class Train {
    private static int firstCapacity;
    private static int secondCapacity;
    private int nextArrival;
    private int trainId;
    private int stationId;
    private int arrivalToFirstStation;
    private static int numTrains = 4;
    private static ArrayList<Train> trainArray = new ArrayList<Train>();
    private int availableRoom;

    /**
     * The constructor below represents a Train object
     * @param trainId
     * @param stationId
     * @param arrivalToFirstStation
     * @param availableRoom
     */
    public Train(int trainId, int stationId, int arrivalToFirstStation, int availableRoom) {
        this.trainId = trainId;
        this.stationId = stationId;
        this.arrivalToFirstStation = arrivalToFirstStation;
        this.availableRoom = availableRoom;
    }

    /**
     * The functions below represents the getter and setter methods for the train.
     *
     */
    public static ArrayList<Train> getTrainArray() { return trainArray; }
    public void setTrainArray(ArrayList<Train> trainArray) { this.trainArray = trainArray; }
    public int getAvailableRoom() { return availableRoom; }
    public void setAvailableRoom(int availableRoom) { this.availableRoom = availableRoom; }
    public int getTrainId() { return trainId; }

    public void setTrainId(int trainId) { this.trainId = trainId; }
    public static int getFirstCapacity() { return firstCapacity; }
    public static void setFirstCapacity(int newFirstCapacity) { firstCapacity = newFirstCapacity; }

    public static int getSecondCapacity() { return secondCapacity; }
    public static void setSecondCapacity(int newSecondCapacity) { secondCapacity = newSecondCapacity; }

    public static int getNumTrains() { return numTrains; }
    public static void setNumTrains(int numTrains) { Train.numTrains = numTrains; }

    /**
     * In order to create new Train instance, a for loop is needed
     */
    public static void createTrainInstance(){
        for(int i = 0; i < numTrains; i++){
            trainArray.add(i, new Train(i, 0, i * 5, firstCapacity));
        }
    }

    public void simulateTimeStep(){

    }
}
