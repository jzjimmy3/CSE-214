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
    private int firstPassengerID = 0;
    private int secondPassengerID = 0;
    private int countPassenger = 0;
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


    public int arrivalTime(){
        //LIRR runs 24/7
        int max = 3; //1440 mins in a 24 hour day
        int min = 0; //At the start of the day
        return (int) ((Math.random() * (max - min)) + min);
    }

    public Passenger createFirstPassenger(int firstPassengerID, int firstArrivalTime, Boolean isFirstClass){
        firstArrivalTime = arrivalTime();
        return new Passenger(firstPassengerID, firstArrivalTime,true);
    };
    public Passenger createSecondPassenger(int secondPassengerID, int secondArrivalTime, Boolean isFirstClass){
        secondPassengerID = ++countPassenger;
        secondArrivalTime = arrivalTime();
        return new Passenger(secondPassengerID, secondArrivalTime,false);
    }

    public void simulateTimeStep(){
        for  ( currentMin = 0; currentMin < 3; currentMin++){

            
//            System.out.println("Time: " + currentMin);
//            for(int currentStation = 0; currentStation <= 7; currentStation++){
//                firstArrivalTime = arrivalTime();
//                if (firstArrivalMineola.occurs() && firstArrivalTime == currentMin){
//                    firstPassengerID = ++countPassenger;
//                    queueArray[currentStation].enqueue(createFirstPassenger(firstPassengerID, firstArrivalTime, true));
//                }
//                secondArrivalTime = arrivalTime();
//                if(secondArrivalMineola.occurs() && secondArrivalTime == currentMin){
//                    secondPassengerID = ++countPassenger;
//                    queueArray[currentStation].enqueue(createSecondPassenger(secondPassengerID, secondArrivalTime, false));
//                }
//                if(currentStation == 3){
//                    System.out.println();
//                    System.out.println("Huntington");
//                    passengerArrival();
//                    System.out.println("Queues:");
//                    System.out.println("First: " + firstClassHuntington);
//                    System.out.println("Second " + secondClassHuntington);
//                }
//                if(currentStation == 2){
//                    System.out.println();
//                    System.out.println("Syosset");
//                    passengerArrival();
//                    System.out.println("Queues:");
//                    System.out.println("First: " + firstClassSyosset);
//                    System.out.println("Second " + secondClassSyosset);
//                }
//                if(currentStation == 1){
//                    System.out.println();
//                    System.out.println("Hicksville");
//                    passengerArrival();
//                    System.out.println("Queues:");
//                    System.out.println("First: " + firstClassHicksville);
//                    System.out.println("Second " + secondClassHicksville);
//                }
//                if(currentStation == 0){
//                    System.out.println();
//                    System.out.println("Mineola");
//                    passengerArrival();
//                    System.out.println("Queues:");
//                    System.out.println("First: " + firstClassMineola);
//                    System.out.println("Second " + secondClassMineola);
//                }
//            }
        }
    }

    public void passengerArrival(){
        if(firstArrivalTime == currentMin){
            System.out.println("First Class Passenger ID " + firstPassengerID + " arrives");
        }else{
            System.out.println("No first class passenger arrives");
        }
        if(secondArrivalTime == currentMin){
            System.out.println("Second Class Passenger ID " + secondPassengerID + " arrives");
        }else{
            System.out.println("No second class passenger arrives");
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
