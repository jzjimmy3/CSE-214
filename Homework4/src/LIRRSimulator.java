//Jimmy Zhang ID: 112844431 CSE 214 RO2

import java.util.Scanner;

/**
 * This class represents the LIRR simulator which is the driver class for all of the code
 * @ Jimmy Zhang
 */
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

    /**
     * The function below is the main function the executes all the code
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
            lirrSimulator();
    }

    /**
     * The function below calls functions in previous classes
     * @throws Exception
     */
    public static void lirrSimulator() throws Exception {
        boolean retry = true;
            while(retry){
                try{
                    Station.setArrivalStatus();
                    retry = false;
                }catch (Exception e){
                    System.out.println("Empty");
                }
            }
        mineola.simulateTimeStep();
        mineola.endSimulation();
    }
}
