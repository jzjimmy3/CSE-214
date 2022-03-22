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
                    input.next();
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

                System.out.print("Please enter number of trains: ");
                Train.setNumTrains(input.nextInt());
                if(Train.getNumTrains() < 4){
                    Train.setNumTrains(4);
                    System.out.println("You must enter at least 4 trains! Number of trains has been set to 4");
                }
                System.out.print("Please enter last arrival time of passengers: ");
                lastArrival = input.nextInt();
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
                }else{
                    System.out.println("No first class passenger arrives");
                }
                if(j <= arrivalArray.length-1){
                    if(arrivalArray[j].occurs()){
                        PassengerID++;
                        System.out.println("Second Class Passenger ID " + PassengerID + " arrives");
                        Passenger p = new Passenger(PassengerID, tempMin,false);
                        queueArray[j].enqueue(p);
                    }
                }else{
                    System.out.println("No second class passenger arrives");
                }
            }
            System.out.println("Queues:");
            System.out.println("First: " + queueArray[i]);
            System.out.println("Second " + queueArray[j]);
            System.out.println();
        }
    }


    /**
     * The function below represents Passenger Embarking the train for those in the first class and second class
     * @param i
     */
    public void passengerEmbarking(int i) {
        if (currentMin % 5 == 0) {
            ArrayList firstList = new ArrayList() {};
            ArrayList secondList = new ArrayList();

            int size = queueArray[i].size();
            for (int k = 0; k < size; k++) {
                int l = i;
                // 1 2 3 4 5 6 7 8
                // 0 1 2 3 4 5 6 7
                if(!queueArray[i].isEmpty()){
                    secondList.add(queueArray[i].peek());
                    queueArray[i].dequeue();
                    queueTotal[i]++;
                }else{
                    System.out.println("No first class Array");
                }
                if(!queueArray[i+1].isEmpty()){
                    firstList.add(queueArray[i+1].peek());
                    queueArray[i+1].dequeue();
                    queueTotal[i+1]++;
                }else{
                    System.out.println("No second class Array");
                }
            }
            if(!firstList.isEmpty()){
                System.out.println("Passengers embarking in first class: " + firstList);
            }else{
                System.out.println("Passengers embarking in first class: [empty]");
            }
            if(!secondList.isEmpty()){
                System.out.println("Passengers embarking in second class: " + secondList);
            }else{
                System.out.println("Passengers embarking in second class: [empty]");
            }

        }
    }

    public void trainArriving(){
        if(currentMin %5 == 0){
            countNumStops++;
        }
        if(currentMin %5 < 15){ //15 is from 3(numStation-1);
            if(currentMin %5 == 0 && currentMin <=15 && countNumStops <= 3){
                System.out.println("Trains:");
                int temp =  2 * countNumStops - 2; // Indexing for queueArray
                for(int i = 0 ; i < countNumStops; i++){
                    System.out.println("\nTrain " + (Train.getTrainArray().get(i).getTrainId()+1) + " arrives at " + stationId(countNumStops-i) +" There are "
                            + queueArray[temp].size() + " in first class and " + queueArray[temp+1].size() + " in second class.");
                    passengerEmbarking(temp);
                    temp = temp -2 ;
                }
                int j = 5;
                for(int i = countNumStops; i < Train.getNumTrains(); i++){
                    System.out.println("\nTrain " + (Train.getTrainArray().get(i).getTrainId()+1) + " will arrive in " +stationId(1) + " in " +  j +" minutes.");
                    j = j + 5;
                }
            }
        }
        if (countNumStops > 3 && countNumStops < Train.getNumTrains()) {
            if(currentMin %5 == 0 && currentMin >= 15){
                System.out.println("Trains:");
                for(int i = 0 ; i < countNumStops-3; i++){ // 4 represents number of stations\
                    System.out.println("\nTrain " + (Train.getTrainArray().get(i).getTrainId()+1) + " no longer running");
                }
                int temp =  2 * 4 - 2; // Indexing for queueArray
                for(int i = countNumStops-3; i <= Train.getNumTrains() + 1; i++){ // 4 represents number of stations
                    if(temp>=0){
                        System.out.println("\nTrain " + (Train.getTrainArray().get(i).getTrainId()+1) + " arrives at " + stationId(countNumStops-i + 1) +" There are "
                                + queueArray[temp].size() + " in first class and " + queueArray[temp+1].size() + " in second class.");
                        passengerEmbarking(temp);
                        temp = temp -2 ;
                    }
                }
            }
        }
        if(countNumStops >= Train.getNumTrains()){
            if(currentMin %5 == 0){
                System.out.println("Trains:");
                for(int i = 0; i < countNumStops-4; i++){ // 4 represents number of stations\
                    System.out.println("\nTrain " + (Train.getTrainArray().get(i).getTrainId()+1) + " no longer running");
                }
                //
                int temp =  2 * 4 - 2; // Indexing for queueArray
                for(int i = countNumStops-4; i <= numStops; i++){ // 4 represents number of stations
                    if(temp>=0  && i < Train.getNumTrains()){
                        System.out.println("\nTrain " + (Train.getTrainArray().get(i).getTrainId()+1) + " arrives at " + stationId(countNumStops-i + 1) +" There are "
                                + queueArray[temp].size() + " in first class and " + queueArray[temp+1].size() + " in second class.");
                        passengerEmbarking(temp);
                        temp = temp -2 ;
                    }
                }
            }
        }
    }

    /**
     * The function below executes whenever all the trains are in motion
     */
    public void trainTransit() {
        if(currentMin %5 !=0 && countNumStops <= 3) {
            if (countNumStops <= 3) {
                for(int k = 1; k <= countNumStops; k++){
                    if(k == countNumStops){
                        int j = 0;
                        for(int i = 5 * countNumStops - 4 ; i <= (countNumStops * timeInterval - 1) ; i++){
                            if(currentMin == i) { j = countNumStops * timeInterval-i; }
                        }
                        System.out.println("Trains:");
                        int temp = countNumStops + 1;
                        for(int i = 0 ; i < countNumStops; i++){
                            System.out.println("\nTrain " + (Train.getTrainArray().get(i).getTrainId()+1) + " will arrive in " + stationId(temp) +" in " + j +" min");
                            temp--;
                        }
                        for(int i = countNumStops; i < Train.getNumTrains(); i++){
                            System.out.println("\nTrain " + (Train.getTrainArray().get(i).getTrainId()+1) + " will arrive in " +stationId(1) + " in " +  j +" min");
                            j = j + 5;
                        }
                    }
                }
            }
            if (countNumStops > 3 && countNumStops < Train.getNumTrains()) {
                for(int k = 1; k <= countNumStops; k++){
                    if(k == countNumStops){
                        int j = 0;
                        for(int i = 5 * countNumStops - 4 ; i <= (countNumStops * timeInterval - 1) ; i++){
                            if(currentMin == i) { j = countNumStops * timeInterval-i; }
                        }
                        System.out.println("Trains:");
                        int temp = 4 + 1; // 4 represents number of Stations
                        for(int i = 0 ; i < 4; i++){ // 4 represents number of Stations
                            System.out.println("\nTrain " + (Train.getTrainArray().get(i).getTrainId()+1) + " will arrive in " + stationId(temp) +" in " + j +" min");
                            temp--;
                        }
                        for(int i = 4; i < Train.getNumTrains(); i++){
                            System.out.println("\nTrain " + (Train.getTrainArray().get(i).getTrainId()+1) + " will arrive in " +stationId(1) + " in " +  j +" min");
                            j = j + 5;
                        }
                    }
                }
            }
            if (countNumStops >= Train.getNumTrains()) {
                for(int k = 1; k <= countNumStops; k++){
                    if(k == countNumStops){
                        int j = 0;
                        for(int i = 5 * countNumStops - 4 ; i <= (countNumStops * timeInterval - 1) ; i++){
                            if(currentMin == i) j = countNumStops * timeInterval-i;
                        }
                        System.out.println("Trains:");
                        System.out.println("CountNum Stops:" + countNumStops);
                        for(int i = 4; i < countNumStops; i++){
                            System.out.println("\nTrain " + (Train.getTrainArray().get(i - 4).getTrainId()+1) + " has stopped picking up passengers.");
                        }
                        int temp = 5; // count 5
                        for(int i = countNumStops; i <= numStops; i++){
                            System.out.println("\nTrain " + (Train.getTrainArray().get(i - 4).getTrainId()+1) + " will arrive in " + stationId(temp) +" in " + j +" min");
                            temp--;
                        }
                    }
                }
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
