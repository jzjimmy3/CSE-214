import java.util.Scanner;

public class LIRRSimulator {
        public static Station mineola, hicksville,syosset,huntington;
        public static Scanner input = new Scanner(System.in);
        public static Train train1, train2, train3, train4;
    static {
        try {
            mineola = new Station();
            hicksville = new Station();
            syosset = new Station();
            huntington = new Station();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) throws Exception {
        System.out.println("Mineola: ");
        mineola.station();
        System.out.println("Hicksville: ");
        hicksville.station();
        System.out.println("Syosset: ");
        syosset.station();
        System.out.println("Huntington: ");
        huntington.station();

        System.out.print("Please enter first class capacity: ");
        train1.setFirstCapacity(input.nextInt());

        System.out.print("Please enter second class capacity: ");
        train1.setFirstCapacity(input.nextInt());

        System.out.println("Please enter number of trains: ");

    }

    public void stationOverview(){

    }
}
