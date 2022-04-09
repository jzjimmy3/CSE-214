import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.Scanner;


public class Storm implements Serializable {
    private String name;
    private double precipitation;
    private double windSpeed;
    private String date = "YYYY-MM-DD";
    public static Scanner input = new Scanner(System.in);

    public Storm(String name, double precipitation, double windSpeed, String date) {
        this.name = name;
        this.precipitation = precipitation;
        this.windSpeed = windSpeed;
        this.date = date;
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public double getPrecipitation() { return precipitation; }
    public void setPrecipitation(double precipitation) { this.precipitation = precipitation; }

    public double getWindSpeed() { return windSpeed; }
    public void setWindSpeed(double windSpeed) { this.windSpeed = windSpeed; }

    public String getDate() { return date; }
    public void setDate(String date) { this.date = date; }

    public static void addStorm(){
        System.out.print("Please enter name: ");
        String name = input.next();
        System.out.print("Please enter date: ");
        String date = input.next();
        System.out.print("Please enter precipitation (cm): ");
        double precipitation = input.nextDouble();
        System.out.print("Please enter wind speed (km/h): ");
        double windSpeed = input.nextDouble();
        StormStatServer.getDatabase().put(name,new Storm(name, precipitation, windSpeed, date));
        System.out.println(name + " added.");
    }

    public static void lookStorm(){
        System.out.print("Please enter name: ");
        String name = input.next();
        Storm storm = null;
        if(StormStatServer.getDatabase().containsKey(name)){
            storm = StormStatServer.getDatabase().get(name);
        }else{
            System.out.println("Storm is not in record and cannot be looked up");
        }
    }
    public static void removeStorm(){
        System.out.print("Please enter name: ");
        String name = input.next();
        Storm storm = null;
        if(StormStatServer.getDatabase().containsKey(name)){
            StormStatServer.getDatabase().remove(name);
            System.out.println("Storm " + name + " has been deleted");
        }else{
            System.out.println("Storm is not in record and cannot be deleted");
        }
    }
    public static void editStorm(){
        System.out.print("Please enter name: ");
        String name = input.next();
        if(StormStatServer.getDatabase().containsKey(name)){
            Storm storm = StormStatServer.getDatabase().get(name);
            System.out.println("In Edit Mode, press enter without any input to leave data unchanged.\n");
            System.out.print("Please enter date: ");
            storm.setDate("sdf");
            System.out.print("Please enter precipitation (cm): ");
            storm.setPrecipitation(input.nextDouble());
            System.out.println("Please enter wind speed (km/h): ");
            storm.setWindSpeed(input.nextDouble());
            System.out.println(name + " added");
        }else{
            System.out.println("Storm is not in record and cannot be edited");
        }
    }

    public class AppendingObjectOutputStream extends ObjectOutputStream {

        public AppendingObjectOutputStream(OutputStream out) throws IOException {
            super(out);
        }

        @Override
        protected void writeStreamHeader() throws IOException {
            // do not write a header, but reset:
            // this line added after another question
            // showed a problem with the original
            reset();
        }

    }

    @Override
    public String toString() {
        return "Storm{" +
                "name='" + name + '\'' +
                ", precipitation=" + precipitation +
                ", windSpeed=" + windSpeed +
                ", date='" + date + '\'' +
                '}';
    }
}
