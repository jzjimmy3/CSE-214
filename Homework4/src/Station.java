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
    private int stationId = 0;
    public int currentMin;
    private static int lastArrival;
    public int numStops = 4+Train.getNumTrains()-1;


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

        System.out.print("Please enter last arrival time of passengers: ");
        lastArrival = input.nextInt();
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

    // keep track of total dequeue from a train!

    // Train ArrayList or ask TA how to fix it up
    public void passengerEmbarkingFirst(int i) {
        if (currentMin % 5 == 0) {
            //Path of Train 1
            ArrayList firstList = new ArrayList() {};
            int size = queueArray[i].size();
            int availableRoom = Train.getTrainArray()[0].getAvailableRoom();
            int counter = 0;
            for (int k = 0; k < Train.getFirstCapacity(); k++) {
                if (k < size) {
                    firstList.add(queueArray[i].peek());
                    queueArray[i].dequeue();
                    counter++;
                }
            }
            Train.getTrainArray()[0].setAvailableRoom(2);
            System.out.println("Passengers embarking in first class: " + firstList);
        }
    }
    public void passengerEmbarkingSecond(int j){
        if (currentMin % 5 == 0) {
            ArrayList secondList = new ArrayList();
            int size = queueArray[j].size();
            if (j < 8) {
                for (int k = 0; k < queueArray[j].size(); k++) {
                    if (k <= size) {
                        secondList.add(queueArray[j].peek());
                        queueArray[j].dequeue();
                    }
                }
            }
            System.out.println("Passengers embarking in second class: " + secondList);
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


    public String arrives(int i){
        return "There are "  + queueArray[i].size() + " passengers in first class and " + queueArray[i-1].size()+ " in second class.";
    }

    public void test(int i){
        System.out.println("Trains:");
        System.out.println("Train" + Train.getTrainArray()[0].getTrainId()+1 + "arrives at" + stationId(1) +"Huntington,"  + arrives(1));
        passengerEmbarkingFirst(1);
        passengerEmbarkingSecond(0);
        System.out.println("Train" + Train.getTrainArray()[1].getTrainId()+1 + "arrives at" + stationId(1) +"Huntington in " + 5 + "minutes");
        passengerEmbarkingFirst(7);
        passengerEmbarkingSecond(6);
        System.out.println("Train" + Train.getTrainArray()[2].getTrainId()+1 + "arrives at" + stationId(1) +"Huntington in " + 10 + "minutes");
        passengerEmbarkingFirst(5);
        passengerEmbarkingSecond(4);
        System.out.println("Train" + Train.getTrainArray()[3].getTrainId()+1 + "arrives at" + stationId(1) +"Huntington in " + 15 + "minutes");
        passengerEmbarkingFirst(3);
        passengerEmbarkingSecond(2);
    }


    public void trainArriving(){
//        case 1: return "Huntington";
//        case 2: return "Syosset";
//        case 3: return "Hicksville";
//        case 4: return "Mineola";
//
        for(int i = 1; i <= numStops; i++){
            int interval = 0;
            int countNumStops = 0;
            if(currentMin == interval && i == countNumStops){



                interval = interval +5;
                countNumStops++;
            }
        }


        if(currentMin == 0){
            System.out.println("Trains:");
            System.out.println("Train 1 arrives at Huntington,"  + arrives(1));
            passengerEmbarkingFirst(1);
            passengerEmbarkingSecond(0);
            System.out.println("Train 2 will arrive at Huntington in 5 minutes.");
            passengerEmbarkingFirst(7);
            passengerEmbarkingSecond(6);
            System.out.println("Train 3 will arrive at Huntington in 10 minutes");
            passengerEmbarkingFirst(5);
            passengerEmbarkingSecond(4);
            System.out.println("Train 4 will arrive at Huntington in 15 minutes");
            passengerEmbarkingFirst(3);
            passengerEmbarkingSecond(2);

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
            System.out.println("Train 4 will arrive at Huntington in 10 minutes");
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

    // Two functions train driving, train arriving
    public void trainTransit() {
        Train.createTrainInstance();
        if (currentMin == 1) {
            System.out.println("Trains:\n" +
                    "Train 1 will arrive in syosset in 4 min \n" +
                    "Train 2 will arrive in Huntington in 4 min\n" +
                    "Train 3 will arrive in Huntington in 9 min\n" +
                    "Train 4 will arrive in Huntington in 14 min");
        }
        if (currentMin == 2) {
            System.out.println("Trains:\n" +
                    "Train 1 will arrive in syosset in 3 min \n" +
                    "Train 2 will arrive in Huntington in 3 min\n" +
                    "Train 3 will arrive in Huntington in 8 min\n" +
                    "Train 4 will arrive in Huntington in 13 min");
        }
        if (currentMin == 3) {
            System.out.println("Trains:\n" +
                    "Train 1 will arrive in syosset in 2 min \n" +
                    "Train 2 will arrive in Huntington in 2 min\n" +
                    "Train 3 will arrive in Huntington in 7 min\n" +
                    "Train 4 will arrive in Huntington in 12 min");
        }
        if (currentMin == 4) {
            System.out.println("Trains:\n" +
                    "Train 1 will arrive in syosset in 1 min \n" +
                    "Train 2 will arrive in Huntington in 1 min\n" +
                    "Train 3 will arrive in Huntington in 6 min\n" +
                    "Train 4 will arrive in Huntington in 11 min");
        }




        if (currentMin == 6) {
            System.out.println("Trains:\n" +
                    "Train 1 will arrive in Hicksville in 4 min \n" +
                    "Train 2 will arrive in Syosset in 4 min\n" +
                    "Train 3 will arrive in Huntington in 4 min\n" +
                    "Train 4 will arrive in Huntington in 9 min");
        }
        if (currentMin == 7) {
            System.out.println("Trains:\n" +
                    "Train 1 will arrive in Hicksville in 3 min \n" +
                    "Train 2 will arrive in Syosset in 3 min\n" +
                    "Train 3 will arrive in Huntington in 3 min\n" +
                    "Train 4 will arrive in Huntington in 8 min");
        }
        if (currentMin == 8) {
            System.out.println("Trains:\n" +
                    "Train 1 will arrive in Hicksville in 2 min \n" +
                    "Train 2 will arrive in Syosset in 2 min\n" +
                    "Train 3 will arrive in Huntington in 2 min\n" +
                    "Train 4 will arrive in Huntington in 7 min");
        }
        if (currentMin == 9) {
            System.out.println("Trains:\n" +
                    "Train 1 will arrive in Hicksville in 1 min \n" +
                    "Train 2 will arrive in Syosset in 1 min\n" +
                    "Train 3 will arrive in Huntington in 1 min\n" +
                    "Train 4 will arrive in Huntington in 6 min");
        }




        if (currentMin == 11) {
            System.out.println("Trains:\n" +
                    "Train 1 will arrive in Mineola in 4 min \n" +
                    "Train 2 will arrive in Hicksville in 4 min\n" +
                    "Train 3 will arrive in Syosset in 4 min\n" +
                    "Train 4 will arrive in Huntington in 4 min");
        }
        if (currentMin == 12) {
            System.out.println("Trains:\n" +
                    "Train 1 will arrive in Mineola in 3 min \n" +
                    "Train 2 will arrive in Hicksville in 3 min\n" +
                    "Train 3 will arrive in Syosset in 3 min\n" +
                    "Train 4 will arrive in Huntington in 3 min");
        }
        if (currentMin == 13) {
            System.out.println("Trains:\n" +
                    "Train 1 will arrive in Mineola in 2 min \n" +
                    "Train 2 will arrive in Hicksville in 2 min\n" +
                    "Train 3 will arrive in Syosset in 2 min\n" +
                    "Train 4 will arrive in Huntington in 2 min");
        }
        if (currentMin == 14) {
            System.out.println("Trains:\n" +
                    "Train 1 will arrive in Mineola in 1 min \n" +
                    "Train 2 will arrive in Hicksville in 1 min\n" +
                    "Train 3 will arrive in Syosset in 1 min\n" +
                    "Train 4 will arrive in Huntington in 1 min");
        }



        if (currentMin == 16) {
            System.out.println("Trains:\n" +
                    "Train 1 will arrive in Jamaica in 4 min \n" +
                    "Train 2 will arrive in Mineola in 4 min\n" +
                    "Train 3 will arrive in Hicksville in 4 min\n" +
                    "Train 4 will arrive in Syosset in 4 min");
        }
        if (currentMin == 17) {
            System.out.println("Trains:\n" +
                    "Train 1 will arrive in Jamaica in 4 min \n" +
                    "Train 2 will arrive in Mineola in 3 min\n" +
                    "Train 3 will arrive in Hicksville in 3 min\n" +
                    "Train 4 will arrive in Syosset in 3 min");
        }
        if (currentMin == 18) {
            System.out.println("Trains:\n" +
                    "Train 1 will arrive in Jamaica in 2 min \n" +
                    "Train 2 will arrive in Mineola in 2 min\n" +
                    "Train 3 will arrive in Hicksville in 2 min\n" +
                    "Train 4 will arrive in Syosset in 2 min");
        }
        if (currentMin == 19) {
            System.out.println("Trains:\n" +
                    "Train 1 will arrive in Jamaica in 1 min \n" +
                    "Train 2 will arrive in Mineola in 1 min\n" +
                    "Train 3 will arrive in Hicksville in 1 min\n" +
                    "Train 4 will arrive in Syosset in 1 min");
        }





        if (currentMin == 21) {
            System.out.println("Trains:\n" +
                    "Train 1 no longer running \n" +
                    "Train 2 will arrive in Jamaica in 4 min\n" +
                    "Train 3 will arrive in Mineola in 4 min\n" +
                    "Train 4 will arrive in Hicksville in 4 min");
        }

        if (currentMin == 22) {
            System.out.println("Trains:\n" +
                    "Train 1 no longer running \n" +
                    "Train 2 will arrive in Jamaica in 3 min\n" +
                    "Train 3 will arrive in Mineola in 3 min\n" +
                    "Train 4 will arrive in Hicksville in 3 min");
        }
        if (currentMin == 23) {
            System.out.println("Trains:\n" +
                    "Train 1 no longer running \n" +
                    "Train 2 will arrive in Jamaica in 2 min\n" +
                    "Train 3 will arrive in Mineola in 2 min\n" +
                    "Train 4 will arrive in Hicksville in 2 min");
        }
        if (currentMin == 24) {
            System.out.println("Trains:\n" +
                    "Train 1 no longer running \n" +
                    "Train 2 will arrive in Jamaica in 1 min\n" +
                    "Train 3 will arrive in Mineola in 1 min\n" +
                    "Train 4 will arrive in Hicksville in 1 min");
        }




        if (currentMin == 26) {
            System.out.println("Trains:\n" +
                    "Train 1 no longer running \n" +
                    "Train 2 no longer running \n" +
                    "Train 3 will arrive in Jamaica in 4 min\n" +
                    "Train 4 will arrive in Mineola in 4 min");
        }
        if (currentMin == 27) {
            System.out.println("Trains:\n" +
                    "Train 1 no longer running \n" +
                    "Train 2 no longer running \n" +
                    "Train 3 will arrive in Jamaica in 3 min\n" +
                    "Train 4 will arrive in Mineola in 3 min");
        }
        if (currentMin == 28) {
            System.out.println("Trains:\n" +
                    "Train 1 no longer running \n" +
                    "Train 2 no longer running \n" +
                    "Train 3 will arrive in Jamaica in 2 min\n" +
                    "Train 4 will arrive in Mineola in 2 min");
        }
        if (currentMin == 29) {
            System.out.println("Trains:\n" +
                    "Train 1 no longer running \n" +
                    "Train 2 no longer running \n" +
                    "Train 3 will arrive in Jamaica in 1 min\n" +
                    "Train 4 will arrive in Mineola in 1 min");
        }

        if (currentMin == 31) {
            System.out.println("Trains:\n" +
                    "Train 1 no longer running \n" +
                    "Train 2 no longer running \n" +
                    "Train 3 no longer running\n" +
                    "Train 4 will arrive in Jamaica in 4 min");
        }
        if (currentMin == 32) {
            System.out.println("Trains:\n" +
                    "Train 1 no longer running \n" +
                    "Train 2 no longer running \n" +
                    "Train 3 no longer running\n" +
                    "Train 4 will arrive in Jamaica in 3 min");
        }

        if (currentMin == 33) {
            System.out.println("Trains:\n" +
                    "Train 1 no longer running \n" +
                    "Train 2 no longer running \n" +
                    "Train 3 no longer running\n" +
                    "Train 4 will arrive in Jamaica in 2 min");
        }

        if (currentMin == 34) {
            System.out.println("Trains:\n" +
                    "Train 1 no longer running \n" +
                    "Train 2 no longer running \n" +
                    "Train 3 no longer running\n" +
                    "Train 4 will arrive in Jamaica in 1 min");
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

    public void simulateTimeStep(){
        Train.createTrainInstance();
        for  ( currentMin = 0; currentMin <= numStops*5; currentMin++){
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
