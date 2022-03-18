//Train starts at Huntington, Syosset, Hicksville, Mineola

import java.util.ArrayList;
import java.util.Scanner;

public class Station {
    public static Scanner input = new Scanner(System.in);
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
    public static BooleanSource firstArrivalHuntington, secondArrivalHuntington, firstArrivalSyosset, secondArrivalSyosset
        ,firstArrivalHicksville, secondArrivalHicksville ,firstArrivalMineola, secondArrivalMineola ;


    private int PassengerID = 0;
    public int currentMin;


    public int getCurrentMin() { return currentMin; }
    public void setCurrentMin(int currentMin) { this.currentMin = currentMin; }


    public static void setArrivalStatus() throws Exception {
        System.out.println("Mineola: ");
        firstArrivalMineola = new BooleanSource(arrivalFirstProb());
        secondArrivalMineola = new BooleanSource(arrivalSecondProb());

        System.out.println("Hicksville: ");
        firstArrivalHicksville = new BooleanSource(arrivalFirstProb());
        secondArrivalHicksville = new BooleanSource(arrivalSecondProb());

        System.out.println("Syosset: ");
        firstArrivalSyosset = new BooleanSource(arrivalFirstProb());
        secondArrivalSyosset = new BooleanSource(arrivalSecondProb());

        System.out.println("Huntington: ");
        firstArrivalHuntington = new BooleanSource(arrivalFirstProb());
        secondArrivalHuntington = new BooleanSource(arrivalSecondProb());

        System.out.print("Please enter first class capacity: ");
        Train.setFirstCapacity(input.nextInt());

        System.out.print("Please enter second class capacity: ");
        Train.setSecondCapacity(input.nextInt());

    }
    public static float arrivalFirstProb(){
        System.out.print("Please enter first class arrival probability: ");
        return input.nextFloat();
    }
    public static float arrivalSecondProb(){
        System.out.print("Please enter second class arrival probability: ");
        return input.nextFloat();
    }

    public void stationOverview(){
        BooleanSource[] arrivalArray = new BooleanSource[]{firstArrivalHuntington, secondArrivalHuntington, firstArrivalSyosset, secondArrivalSyosset
                ,firstArrivalHicksville, secondArrivalHicksville ,firstArrivalMineola, secondArrivalMineola};
        for(int i = 0; i < arrivalArray.length-1; i = i + 2){
            int j = i + 1;
            if(arrivalArray[i].occurs()){
                PassengerID++;
                System.out.println("First Class Passenger ID " + PassengerID + " arrives");
                Passenger p = new Passenger(PassengerID, currentMin,true);
                queueArray[i].enqueue(p);
            }
            if(j <= arrivalArray.length-1){
                if(arrivalArray[j].occurs()){
                    PassengerID++;
                    System.out.println("Second Class Passenger ID " + PassengerID + " arrives");
                    Passenger p = new Passenger(PassengerID, currentMin,false);
                    queueArray[j].enqueue(p);
                }
            }
            System.out.println("Queues:");
            System.out.println("First: " + queueArray[i]);
            System.out.println("Second " + queueArray[j]);
            System.out.println();
        }
    }

    public void trainOverview(){
        PassengerQueue[] queueArray = new PassengerQueue[]{firstClassHuntington,secondClassHuntington,firstClassSyosset,secondClassSyosset,
                firstClassHicksville,secondClassHicksville,firstClassMineola, secondClassMineola,
        };

        System.out.println("Trains: ");
        for(int i = 0; i < 8; i = i + 2){ //numStations
            int j = i + 1;
            if(currentMin %5 != 0) return;
            if(i<=1 ){
                System.out.println("Train 1");
                Train train = Train.getTrainArray()[0];
                passengerEmbarking(i,j);
            }
            if(i>1 && i <= 3){
                System.out.println("Train 2");
                passengerEmbarking(i,j);
            }
            if(i>3 && i <= 5){
                System.out.println("Train 3");
                passengerEmbarking(i,j);
            }
            if(i>5 && i <= 7){
                System.out.println("Train 4");
                passengerEmbarking(i,j);
            }
        }
        trainArriving();
        trainTransit();
    }

    public void passengerEmbarking(int i,int j) {
        if (currentMin % 5 == 0 ) {
            ArrayList firstList = new ArrayList(){};
            ArrayList secondList = new ArrayList(){};

            for (int k = 0; k < queueArray[i].size(); k++) {
                if (k <= Train.getFirstCapacity()) {
                    firstList.add(queueArray[i].peek());
                    queueArray[i].dequeue();
                }
            }
            System.out.println("Passengers embarking in first class: " + firstList);
            if (j < 8) {
                for (int k = 0; k < queueArray[j].size(); k++) {
                    if (k <= Train.getSecondCapacity()) {
                        secondList.add(queueArray[j].peek());
                        queueArray[j].dequeue();
                    }
                }
            }
            System.out.println("Passengers embarking in second class: " + secondList);
        }
    }

    public void simulateTimeStep(){
        for  ( currentMin = 0; currentMin < 6; currentMin++){
            System.out.println("Time: " + currentMin);
            System.out.println("Station Overview");

            System.out.println("Mineola: ");
            stationOverview();
            trainOverview();
        }
    }
    public String arriveState(int arriveNumber){
        switch (arriveNumber){
            case 1: return "arrives";
            case 2: return "will arrive";
            case 3: return "no longer running";
        }
        return "";
    }

    public void trainArriving(){
//        Train.createTrainInstance();
//        int trainId = Train.getTrainArray()[0].getTrainId() + 1;
//        int stationId = Train.getTrainArray()[0].getStationId();
//
//        for(int i = 0; i < 4; i++){
//            for (int j = 0; j < 4; j++){
//                int k = 1;
//                Train.getTrainArray()[i].setTrainId(k);
//                stationId = Train.getTrainArray()[0].getStationId();
//                System.out.println("Train " + trainId + "arrives at" + stationId + ".");
//            }
//        }


        if(currentMin == 0){
            System.out.println("Trains:\n" +
                    "Train 1 arrives at Huntington, There are 0 passengers in first class and 0 in second class.\n" +
                    "Train 2 will arrive at Huntington in 5 minutes.\n" +
                    "Train 3 will arrive at Huntington in 10 minutes.\n" +
                    "Train 4 will arrive at Huntington in 15 minutes.");
        }
        if(currentMin == 5){
            System.out.println("Trains:\n" +
                    "Train 1 arrives at Syosset, There are 0 passengers in first class and 0 in second class\n" +
                    "Train 2 arrives at Huntington, There are 0 passengers in first class and 0 in second class\n" +
                    "Train 3 will arrive at Huntington in 10 minutes.\n"+
                    "Train 4 will arrive at Huntington in 15 minutes.");
        }
        if(currentMin == 10){
            System.out.println("Trains:\n" +
                    "Train 1 arrives at Hicksville, There are 0 passengers in first class and 0 in second class\n" +
                    "Train 2 arrives at Syosset, There are 0 passengers in first class and 0 in second class\n" +
                    "Train 3 arrives at Huntington, There are 0 passengers in first class and 0 in second class\n"+
                    "Train 4 will arrive at Huntington in 15 minutes.");
        }
        if(currentMin == 15){
            System.out.println("Trains:\n" +
                    "Train 1 arrives at Mineola, There are 0 passengers in first class and 0 in second class\n" +
                    "Train 2 arrives at Hicksville, There are 0 passengers in first class and 0 in second class\n" +
                    "Train 3 arrives at Syosset, There are 0 passengers in first class and 0 in second class\n"+
                    "Train 4 arrives at Huntington.There are 0 passengers in first class and 0 in second class");
        }
        if(currentMin == 20){
            System.out.println("Trains:\n" +
                    "Train 1 no longer running\n" +
                    "Train 2 arrives at Mineola, There are 0 passengers in first class and 0 in second class\n" +
                    "Train 3 arrives at Hicksville, There are 0 passengers in first class and 0 in second class\n"+
                    "Train 4 arrives at Huntington.There are 0 passengers in first class and 0 in second class");
        }
    }

    // Two functions train driving, train arriving
    public void trainTransit(){
        Train.createTrainInstance();
        if(currentMin >= 1 && currentMin <=5){
            int j=4;
            for(int i = 0; i < 4; i++){
                int trainID = Train.getTrainArray()[i].getTrainId();
                System.out.println("Train " + (trainID + 1) + " will arrive at Syosset  in "+ j + " Minutes");
                j--;
            }
        }else{
            for(int i = 0; i < 4; i++){
                int trainID = Train.getTrainArray()[i].getTrainId();
                System.out.println("Train " + (trainID + 1) + " will arrive at " + stationId(i+1) + " in "+ currentMin + " Minutes");
            }
        }
    }
    public String stationId(int stationId){
        switch(stationId){
            case 0: return "No station";
            case 1: return "Huntington";
            case 2: return "Syosset";
            case 3: return "Hicksville";
            case 4: return "Mineola";
            default:
                return "Wrong station";
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
