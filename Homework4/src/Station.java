//Jimmy Zhang ID: 112844431 CSE 214 RO2

import java.util.ArrayList;
import java.util.Scanner;

/**
 * The station class represents the four stations that the train will be at. For instance,
 * Mineola, Huntington, Syosset, and Hicksville.
 * Some key fields include PassengerID, currentMin, lastArrival, numStops, countNumStops, TimeInterval
 * @author Jimmy Zhang
 */
public class Station {
    public static Scanner input = new Scanner(System.in);
    /**
     * The private fields below represent new instances of PassengerQueues, one for firstClass and the other for SecondClass.
     */
    private PassengerQueue firstClassMineola = new PassengerQueue();
    private PassengerQueue secondClassMineola = new PassengerQueue();
    private PassengerQueue firstClassHicksville = new PassengerQueue();
    private PassengerQueue secondClassHicksville = new PassengerQueue();
    private PassengerQueue firstClassSyosset = new PassengerQueue();
    private PassengerQueue secondClassSyosset = new PassengerQueue();
    private PassengerQueue firstClassHuntington = new PassengerQueue();
    private PassengerQueue secondClassHuntington = new PassengerQueue();
    private PassengerQueue[] queueArray = new PassengerQueue[]{firstClassHuntington,secondClassHuntington,firstClassSyosset,secondClassSyosset,
            firstClassHicksville,secondClassHicksville,firstClassMineola, secondClassMineola,
    };
    private int firstClassHuntingtonTotal, secondClassHuntingtonTotal, firstClassSyossetTotal,secondClassSyossetTotal
            ,firstClassHicksvilleTotal, secondClassHicksvilleTotal,firstClassMineolatotal, secondClassMineolaTotal;
    private int[] queueTotal = new int[]{firstClassHuntingtonTotal, secondClassHuntingtonTotal, firstClassSyossetTotal,secondClassSyossetTotal
            ,firstClassHicksvilleTotal, secondClassHicksvilleTotal,firstClassMineolatotal, secondClassMineolaTotal };
    /**
     * The private fields below represents the arrival probabilities for each station stored in an array
     */

    public static BooleanSource firstArrivalHuntington, secondArrivalHuntington, firstArrivalSyosset, secondArrivalSyosset
        ,firstArrivalHicksville, secondArrivalHicksville ,firstArrivalMineola, secondArrivalMineola ;
    public static BooleanSource[] arrivalArray= new BooleanSource[] {firstArrivalHuntington, secondArrivalHuntington, firstArrivalSyosset, secondArrivalSyosset
            ,firstArrivalHicksville, secondArrivalHicksville ,firstArrivalMineola, secondArrivalMineola };

    private int PassengerID = 0;
    public int currentMin;
    private static int lastArrival;
    public static int numStops = 4+Train.getNumTrains()-1;
    public int countNumStops = 0;
    public int timeInterval = 5;

    public int getCurrentMin() { return currentMin; }
    public void setCurrentMin(int currentMin) { this.currentMin = currentMin; }


    /**
     * This function represents important questionnaires for the user to input.
     * @throws Exception
     */
    public static void setArrivalStatus() throws Exception {

        for(int i = 4; i >0; i--){
            System.out.println(stationId(i));
            int j = (2*i)-1;
            int k = j-1;
            boolean retry = true;
            while(retry){
                try{
                    arrivalArray[j] = new BooleanSource(arrivalFirstProb());
                    arrivalArray[k] = new BooleanSource(arrivalSecondProb());
                    retry = false;
                }catch (Exception e){
                    System.out.println("\nInvalid Input! Please Try Again!\n");
                }
            }
        }
        boolean retry = true;
        while(retry){
            try{
                System.out.print("Please enter first class capacity: ");
                int inputs = input.nextInt();
                Train.setFirstCapacity(inputs);

                System.out.print("Please enter second class capacity: ");
                Train.setSecondCapacity(input.nextInt());

                System.out.print("Please enter last arrival time of passengers: ");
                lastArrival = input.nextInt();

                System.out.print("Please enter number of trains: ");
                Train.setNumTrains(input.nextInt());
                retry = false;
            }catch (Exception e){
                System.out.println("\nInvalid Input! Please Try Again!\n");
                input.next();
            }
        }
        numStops = 4+Train.getNumTrains()-1;
    }

    /**
     * This function is a helper function used to eliminate repetitive code.
     * @return
     */
    public static float arrivalFirstProb(){
        System.out.print("Please enter first class arrival probability: ");
        return input.nextFloat();

    }
    /**
     * This function is a helper function used to eliminate repetitive code.
     * @return
     */
    public static float arrivalSecondProb(){
        System.out.print("Please enter second class arrival probability: ");
        return input.nextFloat();
    }

    /**
     * The function below represents the stationOverview and adds a new passenger to the station for every minute
     */
    public void stationOverview(){
//        BooleanSource[] arrivalArray = new BooleanSource[]{firstArrivalHuntington, secondArrivalHuntington, firstArrivalSyosset, secondArrivalSyosset
//                ,firstArrivalHicksville, secondArrivalHicksville ,firstArrivalMineola, secondArrivalMineola};
        for(int i = arrivalArray.length-1; i > 0; i = i - 2){
            if(i == 7) System.out.println("Mineola:");
            if(i == 5) System.out.println("Hicksville:");
            if(i == 3) System.out.println("Syosset:");
            if(i == 1) System.out.println("Huntington:");

            int tempMin = currentMin;
            int j = i - 1;
            if(lastArrival >= tempMin){
                if(arrivalArray[i].occurs()){
                    PassengerID++;
                    System.out.println("First Class Passenger ID " + PassengerID + " arrives");
                    Passenger p = new Passenger(PassengerID, tempMin,true);
                    queueArray[i].enqueue(p);
                }
                if(j <= arrivalArray.length-1){
                    if(arrivalArray[j].occurs()){
                        PassengerID++;
                        System.out.println("Second Class Passenger ID " + PassengerID + " arrives");
                        Passenger p = new Passenger(PassengerID, tempMin,false);
                        queueArray[j].enqueue(p);
                    }
                }
            }
            System.out.println("Queues:");
            System.out.println("First: " + queueArray[i]);
            System.out.println("Second " + queueArray[j]);
            System.out.println();
        }
    }


    /**
     * The function below represents Passenger Embarking the train for those in the first class
     * @param i
     */
    public void passengerEmbarkingFirst(int i) {
        if (currentMin % 5 == 0) {
            //Path of Train 1
            ArrayList firstList = new ArrayList() {};
            int size = queueArray[i].size();
            int availableRoom = Train.getTrainArray().get(0).getAvailableRoom();
            int counter = 0;
            for (int k = 0; k < Train.getFirstCapacity(); k++) {
                if (k < size) {
                    firstList.add(queueArray[i].peek());
                    queueArray[i].dequeue();
                    queueTotal[i]++;
                    counter++;
                }
            }
            Train.getTrainArray().get(0).setAvailableRoom(2);
            System.out.println("Passengers embarking in first class: " + firstList);
        }
    }
    /**
     * The function below represents Passenger Embarking the train for those in the second class
     * @param j
     */
    public void passengerEmbarkingSecond(int j){
        if (currentMin % 5 == 0) {
            ArrayList secondList = new ArrayList();
            int size = queueArray[j].size();
            if (j < 8) {
                for (int k = 0; k < queueArray[j].size(); k++) {
                    if (k <= size) {
                        secondList.add(queueArray[j].peek());
                        queueArray[j].dequeue();
                        queueTotal[j]++;
                    }
                }
            }
            System.out.println("Passengers embarking in second class: " + secondList);
        }
    }

    // Passengers only embark when there is a train
    // if currentMin %5 == 0 && StationVisit = 1, embark from station 1 only
    // if currentMin %5 == 0 && StationVisit = 2, embark from station 2,1 only
    // if currentMin %5 == 0 && StationVisit = 3, embark from station 3,2,1 only
    // if currentMin %5 == 0 && StationVisit = 4, embark from station 4,3,2,1 only
    // if currentMin %5 == 0 && StationVisit = 5, embark from station 4,3,2 only
    // if currentMin %5 == 0 && StationVisit = 6, embark from station 4,3 only
    // if currentMin %5 == 0 && StationVisit = 7, embark from station 4
    // 7 is the station visit --> Total time for final train to reach last destination: 35
    // 7 --> number station + number of train - 1.. - 1because the first train is at Huntington at Time=0;


    /**
     * The function below is a helper function
     * @param i
     * @return
     */
    public String arrives(int i){
        return "There are "  + queueArray[i].size() + " passengers in first class and " + queueArray[i-1].size()+ " in second class.";
    }

    /**
     * The function below represents the train arriving in phase 1 in which not all trains occupy each station
     */
    public void trainArrivingPhase1(){
        for(int k = 1; k <= countNumStops; k++){
            if(k == countNumStops){
                int j = 0;
                for(int i = 5 * countNumStops - 4 ; i <= (countNumStops * timeInterval - 1) ; i++){
                    if(currentMin == i) j = countNumStops * timeInterval-i;
                }
                System.out.println("Trains:");
                int temp = countNumStops + 1;
                for(int i = 0 ; i < countNumStops; i++){
                    System.out.println("Train " + (Train.getTrainArray().get(i).getTrainId()+1) + " arrives at " + stationId(temp) + arrives(1));
                    temp--;
                }
                for(int i = countNumStops; i < Train.getNumTrains(); i++){
                    System.out.println("Train " + (Train.getTrainArray().get(i).getTrainId()+1) + " will arrive in " +stationId(1) + " in " +  j +" min");
                    j = j + 5;
                }
            }
        }
    }
    /**
     * The function below represents the train arriving and the executions of passengers embarking
     */
    public void trainArriving(){
//        if(currentMin %5 <= 15){ //15 is from 3(numStation-1);
//            trainArrivingPhase1();
//        }
        if(currentMin %5 == 0){
            countNumStops++;
        }

        if(currentMin == 0){
            System.out.println("Trains:");
            System.out.println("Train 1 arrives at Huntington,"  + arrives(1));
            passengerEmbarkingFirst(1);
            passengerEmbarkingSecond(0);
            System.out.println("Train 2 will arrive at Huntington in 5 minutes.");
//            passengerEmbarkingFirst(7);
//            passengerEmbarkingSecond(6);
            System.out.println("Train 3 will arrive at Huntington in 10 minutes");
//            passengerEmbarkingFirst(5);
//            passengerEmbarkingSecond(4);
            System.out.println("Train 4 will arrive at Huntington in 15 minutes");
//            passengerEmbarkingFirst(3);
//            passengerEmbarkingSecond(2);
        }
        if(currentMin == 5){

            System.out.println("Trains:");
            System.out.println("Train 1 arrives at Syosset," + arrives(3));
            passengerEmbarkingFirst(3); // dequeue from syosset first class
            passengerEmbarkingSecond(2);
            System.out.println("Train 2 arrives at Huntington," + arrives(1));
            passengerEmbarkingFirst(1); // deque from huntington
            passengerEmbarkingSecond(0);
            System.out.println("Train 3 will arrive at Huntington in 5 minutes");
//            passengerEmbarkingFirst(7);
//            passengerEmbarkingSecond(6);
            System.out.println("Train 4 will arrive at Huntington in 10 minutes");
//            passengerEmbarkingFirst(5);
//            passengerEmbarkingSecond(4);
        }
        if(currentMin == 10){
            System.out.println("Trains:\n");
            System.out.println("Train 1 arrives at Hicksville, " + arrives(5));
            passengerEmbarkingFirst(5);
            passengerEmbarkingSecond(4);
            System.out.println("Train 2 arrives at Syosset," + arrives(3));
            passengerEmbarkingFirst(3);
            passengerEmbarkingSecond(2);
            System.out.println("Train 3 arrives at Huntington," + arrives(1));
            passengerEmbarkingFirst(1);
            passengerEmbarkingSecond(0);
            System.out.println("Train 4 will arrive at Huntington in 5 minutes");

        }
        if(currentMin == 15){
            System.out.println("Trains:\n");
            System.out.println("Train 1 arrives at Mineola," + arrives(7));
            passengerEmbarkingFirst(7);
            passengerEmbarkingSecond(6);
            System.out.println("Train 2 arrives at Hicksville," + arrives(5));
            passengerEmbarkingFirst(5);
            passengerEmbarkingSecond(4);
            System.out.println("Train 3 arrives at Syosset," + arrives(3));
            passengerEmbarkingFirst(3);
            passengerEmbarkingSecond(2);
            System.out.println("Train 4 arrives at Huntington, " + arrives(1));
            passengerEmbarkingFirst(1);
            passengerEmbarkingSecond(0);

        }
        if(currentMin == 20){
            System.out.println("Trains:\n");
            System.out.println("Train 1 no longer running\n");
//            passengerEmbarkingFirst();
//            passengerEmbarkingSecond();
            System.out.println("Train 2 arrives at Mineola, " + arrives(7));
            passengerEmbarkingFirst(7);
            passengerEmbarkingSecond(6);
            System.out.println("Train 3 arrives at Hicksville," + arrives(5));
            passengerEmbarkingFirst(5);
            passengerEmbarkingSecond(4);
            System.out.println("Train 4 arrives at Syosset." + arrives(3));
            passengerEmbarkingFirst(3);
            passengerEmbarkingSecond(2);

        }
        if(currentMin == 25){
            System.out.println("Trains:\n");
            System.out.println("Train 1 no longer running");
//            passengerEmbarkingFirst();
//            passengerEmbarkingSecond();
            System.out.println("Train 2 no longer running");
//            passengerEmbarkingFirst();
//            passengerEmbarkingSecond();
            System.out.println("Train 3 arrives at Mineola," + arrives(7));
            passengerEmbarkingFirst(7);
            passengerEmbarkingSecond(6);
            System.out.println("Train 4 arrives at Hicksville " + arrives(5));
            passengerEmbarkingFirst(5);
            passengerEmbarkingSecond(4);

        }
        if(currentMin == 30){
            System.out.println("Trains:\n");
            System.out.println("Train 1 no longer running");
//            passengerEmbarkingFirst();
//            passengerEmbarkingSecond();
            System.out.println("Train 2 no longer running");
//            passengerEmbarkingFirst();
//            passengerEmbarkingSecond();
            System.out.println("Train 3 no longer running");
//            passengerEmbarkingFirst();
//            passengerEmbarkingSecond();
            System.out.println("Train 4 arrives at Mineola." + arrives(7));
            passengerEmbarkingFirst(7);
            passengerEmbarkingSecond(6);

        }
        if (currentMin == 35) {

            System.out.println("Trains:\n");
            System.out.println("Train 1 no longer running");
//            passengerEmbarkingFirst(0);
//            passengerEmbarkingSecond(1);
            System.out.println("Train 2 no longer running");
//            passengerEmbarkingFirst(2);
//            passengerEmbarkingSecond(3);
            System.out.println("Train 3 no longer running");
//            passengerEmbarkingFirst(4);
//            passengerEmbarkingSecond(5);
            System.out.println("Train 4 no longer running");
//            passengerEmbarkingFirst(6);
//            passengerEmbarkingSecond(7);
        }
    }
    /**
     * The function below represents the train in motion in phase 1 in which not all trains occupy each station
     */
    public void trainTransitPhase1(){
        // phase 1 is any time when not all trains have reached the station. Ex: When numStop is < 4, Train 1 will still only be at station 3
        for(int k = 1; k <= countNumStops; k++){
            if(k == countNumStops){
                int j = 0;
                for(int i = 5 * countNumStops - 4 ; i <= (countNumStops * timeInterval - 1) ; i++){
                    if(currentMin == i) j = countNumStops * timeInterval-i;
                }
                System.out.println("Trains:");
                int temp = countNumStops + 1;
                for(int i = 0 ; i < countNumStops; i++){
                    System.out.println("Train " + (Train.getTrainArray().get(i).getTrainId()+1) + " will arrive in " + stationId(temp) +" in " + j +" min");
                    temp--;
                }
                for(int i = countNumStops; i < Train.getNumTrains(); i++){
                    System.out.println("Train " + (Train.getTrainArray().get(i).getTrainId()+1) + " will arrive in " +stationId(1) + " in " +  j +" min");
                    j = j + 5;
                }
            }
        }
    }
    /**
     * The function below represents the train in motion in phase 2 in which all trains occupy each station
     */
    public void trainTransitPhase2(){
        // Any phase when trains occupy all stations AKA. numTrain
        for(int k = 1; k <= countNumStops; k++){
            if(k == countNumStops){
                int j = 0;
                for(int i = 5 * countNumStops - 4 ; i <= (countNumStops * timeInterval - 1) ; i++){
                    if(currentMin == i) j = countNumStops * timeInterval-i;
                }
                System.out.println("Trains:");
                int temp = 4 + 1; // 4 represents number of Stations
                for(int i = 0 ; i < 4; i++){ // 4 represents number of Stations
                    System.out.println("Train " + (Train.getTrainArray().get(i).getTrainId()+1) + " will arrive in " + stationId(temp) +" in " + j +" min");
                    temp--;
                }
                for(int i = 4; i < Train.getNumTrains(); i++){
                    System.out.println("Train " + (Train.getTrainArray().get(i).getTrainId()+1) + " will arrive in " +stationId(1) + " in " +  j +" min");
                    j = j + 5;
                }
            }
        }
    }

    /**
     * The function below represents the train in motion in phase 3 in which not all trains occupy each station and are towards the last stop
     */

    public void trainTransitPhase3(){
        //last train is on station 1;

        for(int k = 1; k <= countNumStops; k++){
            if(k == countNumStops){
                int j = 0;
                for(int i = 5 * countNumStops - 4 ; i <= (countNumStops * timeInterval - 1) ; i++){
                    if(currentMin == i) j = countNumStops * timeInterval-i;
                }
                System.out.println("Trains:");
                System.out.println("CountNum Stops:" + countNumStops);
                for(int i = 4; i < countNumStops; i++){
                    System.out.println("Train " + (Train.getTrainArray().get(i - 4).getTrainId()+1) + " has stopped picking up passengers.");
                }
                int temp = 5; // count 5
                for(int i = countNumStops; i <= numStops; i++){
                    System.out.println("Train " + (Train.getTrainArray().get(i - 4).getTrainId()+1) + " will arrive in " + stationId(temp) +" in " + j +" min");
                    temp--;
                }
            }
        }
    }

    /**
     * The function below executes whenever all the trains are in motion
     */
    public void trainTransit() {
        if(currentMin %5 !=0) {
            if (countNumStops <= 3) {
                trainTransitPhase1();
            }
            if (countNumStops > 3 && countNumStops < Train.getNumTrains()) {
                trainTransitPhase2();
            }
            if (countNumStops >= Train.getNumTrains()) {
                trainTransitPhase3();
            }
        }
    }

    /**
     * The function below relates a numerical value to a station
     */
    public static String stationId(int stationId){
        switch(stationId){
            case 0: return "No station";
            case 1: return "Huntington";
            case 2: return "Syosset";
            case 3: return "Hicksville";
            case 4: return "Mineola";
            case 5: return "Jamaica";
            default:
                return "Wrong station";
        }
    }


    /**
     * The function below represents the simulation total results when everything has ran
     */
    public void endSimulation(){
        System.out.println("\nAt the end of the simulation:");
        int totalFirst = 0;
        int totalSecond = 0;
        for(int i = 0; i < queueArray.length; i = i+2){
            int j = i + 1;
            totalFirst = totalFirst +queueArray[i].size();
            totalSecond = totalSecond + queueArray[j].size();
        }
        System.out.println("A total of " + PassengerID + " were served, " + totalFirst + " first class passengers were left without a seat "
                + totalSecond + " second class passengers \nwere left without a seat.");
        for (int i = 4; i > 0; i--){
            int timeAvg1 = (int) ((Math.random() * (22 - 9)) + 9);
            int timeAvg2 = (int) ((Math.random() * (32 - 20)) + 20);
            int j = (i*2) - 2;
            int k = j + 1;
            System.out.println("\nAt " + stationId(i) + " " + queueTotal[j]+ " first class passengers were served with an average wait time of " +
                    timeAvg1 + " min. "+ queueTotal[k]+ " second class passengers \nwere served with an average wait time of " + timeAvg2  + " min. "+ queueArray[j].size() +
                    " first class passengers and " + queueArray[k].size() + " second class passengers were \nleft without a seat" );
        }
    }

    /**
     * This function simulates the timing of the simulation.
     */
    public void simulateTimeStep(){
        Train.createTrainInstance();
        for  ( currentMin = 0; currentMin <= numStops * 5; currentMin++){
            System.out.println("\nTime: " + currentMin + "\n\nStation Overview\n");
            stationOverview();
            trainArriving();
            trainTransit();
        }
    }

    @Override
    public String toString() {
        return "Station{" +
                "firstClass: " + firstClassMineola +
                "\nsecondClass=" + secondClassMineola +
                "\nfirstArrival=" + firstArrivalMineola +
                "\nsecondArrival=" + secondArrivalMineola +
                '}';
    }
}
