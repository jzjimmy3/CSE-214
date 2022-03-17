import java.util.Scanner;

public class LIRRSimulator {
        public static Station mineola, hicksville,syosset,huntington;
        public static Scanner input = new Scanner(System.in);
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
        lirrSimulator();

    }
    public static void lirrSimulator() throws Exception {
        Station.setArrivalStatus();
        mineola.simulateTimeStep();
    }
}
