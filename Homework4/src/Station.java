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
    private PassengerQueue[] queueArray = new PassengerQueue[]{firstClassMineola, secondClassMineola,firstClassHicksville,secondClassHicksville
            ,firstClassSyosset,secondClassSyosset, firstClassHuntington,secondClassHuntington
    };
    public static BooleanSource firstArrivalMineola, secondArrivalMineola, firstArrivalHicksville, secondArrivalHicksville
    , firstArrivalSyosset, secondArrivalSyosset, firstArrivalHuntington, secondArrivalHuntington;

    private int PassengerID = 0;
    public int currentMin;


    public int getCurrentMin() { return currentMin; }
    public void setCurrentMin(int currentMin) { this.currentMin = currentMin; }


    public static void setArrivalStatus() throws Exception {
        System.out.println("Mineola: ");
        firstArrivalMineola = new BooleanSource(arrivalFirstProb());
        System.out.println(firstArrivalMineola);
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
        BooleanSource[] arrivalArray = new BooleanSource[]{firstArrivalMineola, secondArrivalMineola, firstArrivalHicksville, secondArrivalHicksville
                , firstArrivalSyosset, secondArrivalSyosset, firstArrivalHuntington, secondArrivalHuntington};
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
        PassengerQueue[] queueArray = new PassengerQueue[]{firstClassMineola, secondClassMineola,firstClassHicksville,secondClassHicksville
                ,firstClassSyosset,secondClassSyosset, firstClassHuntington,secondClassHuntington
        };
        for(int i = 0; i < 8; i = i + 2){ //numStations
            int j = i + 1;
            if(i<=1){
                System.out.println("Station 1");
                passengerEmbarking(i,j);
            }
            if(i>1 && i <= 3){
                System.out.println("Station 2");
                passengerEmbarking(i,j);
            }
            if(i>3 && i <= 5){
                System.out.println("Station 3");
                passengerEmbarking(i,j);
            }
            if(i>5 && i <= 7){
                System.out.println("Station 4");
                passengerEmbarking(i,j);
            }
        }
    }

    public void passengerEmbarking(int i,int j) {
        if (currentMin % 5 == 0 ) {
            for (int k = 0; k < queueArray[i].size(); k++) {
                if (k <= Train.getFirstCapacity()) {
                    Passenger p = queueArray[i].peek();
                    queueArray[i].dequeue();
                    System.out.println("Passengers embarking in first class: " + p);

                }
            }
            if (j < 8) {
                for (int k = 0; k < queueArray[j].size(); k++) {
                    if (k <= Train.getSecondCapacity()) {
                        Passenger p = queueArray[j].peek();
                        queueArray[j].dequeue();
                        System.out.println("Passengers embarking in second class: " + p);
                    }
                }
            }
        }
    }

    public void simulateTimeStep(){
        for  ( currentMin = 0; currentMin < 6; currentMin++){
            System.out.println("Time: " + currentMin);
            System.out.println("Station Overview");

            System.out.println("Mineola: ");
            stationOverview();
            trainOverview();

//            int numOfStation = 1;
//            if(currentMin % 5 == 0 && numOfStation == 1){
//                System.out.println("HELLO");
//                System.out.println(Train.getTrainArray()[0].getFirstCapacity());
//                System.out.println(firstClassMineola.size());
//                for(int i = 0; i < firstClassMineola.size(); i++){
//                    if(i < Train.getTrainArray()[0].getFirstCapacity()){
//                        Passenger p = firstClassMineola.peek();
//                        firstClassMineola.dequeue();
//                        System.out.println("Passengers embarking in first class: " + p);
//                    }
//                }
//                for(int i = 0; i < secondClassMineola.size(); i++){
//                    if(i < Train.getTrainArray()[0].getFirstCapacity()){
//                        Passenger p = secondClassMineola.peek();
//                        secondClassMineola.dequeue();
//                        System.out.println("Passengers embarking in second class: " + p);
//                    }
//                }
//                numOfStation++;
//            }
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
