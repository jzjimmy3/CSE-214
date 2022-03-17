import java.util.Scanner;

public class Station {
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
    private static BooleanSource firstArrivalMineola;
    private static BooleanSource secondArrivalMineola;
    private static BooleanSource firstArrivalHicksville;
    private static BooleanSource secondArrivalHicksville;
    private static BooleanSource firstArrivalSyosset;
    private static BooleanSource secondArrivalSyosset;
    private static BooleanSource firstArrivalHuntington;
    private static BooleanSource secondArrivalHuntington;

    private int firstArrivalTime;
    private int secondArrivalTime;
    private int PassengerID = 0;
    public int currentMin;
    public int totalTime = 1440; // number of trains times 5

    public PassengerQueue getFirstClassMineola() { return firstClassMineola; }
    public void setFirstClassMineola(PassengerQueue firstClassMineola) { this.firstClassMineola = firstClassMineola; }

    public PassengerQueue getSecondClassMineola() { return secondClassMineola; }
    public void setSecondClassMineola(PassengerQueue secondClassMineola) { this.secondClassMineola = secondClassMineola; }

    public BooleanSource getFirstArrivalMineola() { return firstArrivalMineola; }
    public void setFirstArrivalMineola(BooleanSource firstArrivalMineola) { this.firstArrivalMineola = firstArrivalMineola; }

    public BooleanSource getSecondArrivalMineola() { return secondArrivalMineola; }
    public void setSecondArrivalMineola(BooleanSource secondArrivalMineola) { this.secondArrivalMineola = secondArrivalMineola; }

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
    }

    public static float arrivalFirstProb(){
        Scanner input = new Scanner(System.in);
        System.out.print("Please enter first class arrival probability: ");
        return input.nextFloat();
    }
    public static float arrivalSecondProb(){
        Scanner input = new Scanner(System.in);
        System.out.print("Please enter second class arrival probability: ");
        return input.nextFloat();
    }

    public Passenger createFirstPassenger(int firstPassengerID, int firstArrivalTime, Boolean isFirstClass){
        return new Passenger(firstPassengerID, firstArrivalTime,true);
    };
    public Passenger createSecondPassenger(int secondPassengerID, int secondArrivalTime, Boolean isFirstClass){
//        secondPassengerID = ++countPassenger;
        return new Passenger(secondPassengerID, secondArrivalTime,false);
    }

    public void simulateTimeStep(){
        for  ( currentMin = 0; currentMin < 3; currentMin++){
            System.out.println("Time: " + currentMin);
            System.out.println("Station Overview");

            System.out.println("Mineola: ");
            if(firstArrivalMineola.occurs()){
                PassengerID++;
                System.out.println("First Class Passenger ID " + PassengerID + " arrives");
                Passenger p = new Passenger(PassengerID, currentMin,true);
                firstClassMineola.enqueue(p);
            }
            if(secondArrivalMineola.occurs()){
                PassengerID++;
                System.out.println("Second Class Passenger ID " + PassengerID + " arrives");
                Passenger p = new Passenger(PassengerID, currentMin,false);
                secondClassMineola.enqueue(p);

            }
            System.out.println("Queues:");
            System.out.println("First: " + firstClassMineola);
            System.out.println("Second " + secondClassMineola);
            System.out.println();

            System.out.println("Hicksville: ");
            if(firstArrivalHicksville.occurs()){
                PassengerID++;
                System.out.println("First Class Passenger ID " + PassengerID + " arrives");
                Passenger p = new Passenger(PassengerID, currentMin,true);
                firstClassHicksville.enqueue(p);
            }
            if(secondArrivalHicksville.occurs()){
                PassengerID++;
                System.out.println("Second Class Passenger ID " + PassengerID + " arrives");
                Passenger p = new Passenger(PassengerID, currentMin,false);
                secondClassHicksville.enqueue(p);

            }
            System.out.println("Queues:");
            System.out.println("First: " + firstClassHicksville);
            System.out.println("Second " + secondClassHicksville);
            System.out.println();

            System.out.println("Syosset: ");
            if(firstArrivalSyosset.occurs()){
                PassengerID++;
                System.out.println("First Class Passenger ID " + PassengerID + " arrives");
                Passenger p = new Passenger(PassengerID, currentMin,true);
                firstClassSyosset.enqueue(p);
            }
            if(secondArrivalSyosset.occurs()){
                PassengerID++;
                System.out.println("Second Class Passenger ID " + PassengerID + " arrives");
                Passenger p = new Passenger(PassengerID, currentMin,false);
                secondClassSyosset.enqueue(p);
            }
            System.out.println("Queues:");
            System.out.println("First: " + firstClassSyosset);
            System.out.println("Second " + secondClassSyosset);
            System.out.println();

            System.out.println("Huntington: ");
            if(firstArrivalHuntington.occurs()){
                PassengerID++;
                System.out.println("First Class Passenger ID " + PassengerID + " arrives");
                Passenger p = new Passenger(PassengerID, currentMin,true);
                firstClassHuntington.enqueue(p);
            }
            if(secondArrivalHuntington.occurs()){
                PassengerID++;
                System.out.println("Second Class Passenger ID " + PassengerID + " arrives");
                Passenger p = new Passenger(PassengerID, currentMin,false);
                secondClassHuntington.enqueue(p);
            }
            System.out.println("Queues:");
            System.out.println("First: " + firstClassHuntington);
            System.out.println("Second " + secondClassHuntington);
            System.out.println();

            int trainStation = 0; // 1 means train is at mineola, 2 means hick's, 3 means syosset, 4 means huntington

            if(currentMin % 5 == 0){
                trainStation++;
                int j = Train.getTrainArray().length
                for(int i = 0; i<Train.getTrainArray().length; i++){
                    j--;
                    Train.getTrainArray()[i] = Train.getTrainArray()[2];
                }
            }
        }
    }

    public void stationOverview(){

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
