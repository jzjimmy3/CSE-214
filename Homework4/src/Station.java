import java.util.Scanner;

public class Station {
    private PassengerQueue firstClass = new PassengerQueue();
    private PassengerQueue secondClass = new PassengerQueue();
    private BooleanSource firstArrival;
    private BooleanSource secondArrival;
    public int currentMin;
    public int totalTime = 100; // number of trains times 5

    public Station() throws Exception {
    }

    public void station() throws Exception {
        this.setFirstArrival(new BooleanSource(arrivalFirstProp()));
        this.setSecondArrival(new BooleanSource(arrivalSecondProp()));
    }

    public static float arrivalFirstProp(){
        Scanner input = new Scanner(System.in);
        System.out.print("Please enter first class arrival probability: ");
        return input.nextFloat();
    }
    public static float arrivalSecondProp(){
        Scanner input = new Scanner(System.in);
        System.out.print("Please enter second class arrival probability: ");
        return input.nextFloat();
    }

    public PassengerQueue getFirstClass() {
        return firstClass;
    }

    public void setFirstClass(PassengerQueue firstClass) {
        this.firstClass = firstClass;
    }

    public PassengerQueue getSecondClass() {
        return secondClass;
    }

    public void setSecondClass(PassengerQueue secondClass) {
        this.secondClass = secondClass;
    }

    public BooleanSource getFirstArrival() {
        return firstArrival;
    }

    public void setFirstArrival(BooleanSource firstArrival) {
        this.firstArrival = firstArrival;
    }

    public BooleanSource getSecondArrival() {
        return secondArrival;
    }

    public void setSecondArrival(BooleanSource secondArrival) {
        this.secondArrival = secondArrival;
    }

    public void simulateTimeStep(){
        for  ( currentMin = 1; currentMin <= totalTime; currentMin++);

    }

    @Override
    public String toString() {
        return "Station{" +
                "firstClass=" + firstClass +
                ", secondClass=" + secondClass +
                ", firstArrival=" + firstArrival +
                ", secondArrival=" + secondArrival +
                '}';
    }
}
