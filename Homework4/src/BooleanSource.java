public class BooleanSource {
    private double probability;
    public BooleanSource(double p) throws Exception {
        if (p < 0.0 || p > 1.0)
            throw new IllegalArgumentException();
        probability = p;
    }
    public boolean occurs() {
        return (Math.random() < probability);
    }

    public double getProbability() {
        return probability;
    }

    public void setProbability(double probability) {
        this.probability = probability;
    }

    @Override
    public String toString() {
        return "BooleanSource{" +
                "probability=" + probability +
                '}';
    }
}
