//Jimmy Zhang ID: 112844431 CSE 214 RO2

/**
 * This class determines the arrival probability of a passenger
 * There is a private double probability field
 * @author Jimmy Zhang
 */
public class BooleanSource {
    private double probability;

    /**
     * This function sets probability equal to the parameter if certain conditions are met
     * @param p
     * @throws Exception
     */
    public BooleanSource(double p) throws Exception {
            if (p < 0.0 || p > 1.0)
                throw new IllegalArgumentException();
            probability = p;

    }

    /**
     * This function determines if a passenger arrives or not
     * @return boolean
     */
    public boolean occurs() {
        return (Math.random() < probability);
    }

    /**
     * The functions below represents the getter and setter methods
     * @return boolean
     */

    public double getProbability() {
        return probability;
    }

    public void setProbability(double probability) {
        this.probability = probability;
    }
    /**
     * The toString function prints out the BooleanSource
     */
    @Override
    public String toString() {
        return "BooleanSource{" +
                "probability=" + probability +
                '}';
    }
}
