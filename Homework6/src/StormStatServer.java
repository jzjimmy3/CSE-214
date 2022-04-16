import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Set;

public class StormStatServer {
    public static Scanner input = new Scanner(System.in);
    public static boolean quitValue = false;
    private static HashMap<String, Storm> database = new HashMap<String, Storm>();

    public static HashMap<String, Storm> getDatabase() {
        return database;
    }

    public static void setDatabase(HashMap<String, Storm> database) {
        StormStatServer.database = database;
    }



    public static void main(String[] args) throws Exception {
        System.out.println("\nWelcome to the StormStatServer, we may not be able to make it rain, but we sure can tell you when it happened!\n");
        displayMenu();
//        writeToFile();
        File file = new File("hurricane.ser");
        if(file.isFile()){
            readFile();
        }else{
            System.out.println("No previous data found\n");
        }
        while(!quitValue){
            menuOptions();
        }
    }
    public static void displayMenu(){
        System.out.println("" +
                "    A) Add A Storm\n" +
                "    L) Look Up A Storm\n" +
                "    D) Delete A Storm\n" +
                "    E) Edit Storm Data\n" +
                "    R) Print Storms Sorted By Rainfall\n" +
                "    W) W-Print Storms by Windspeed\n" +
                "    X) Save and quit\n" +
                "    Q) Quit and delete saved data ");
    }

    public static void menuOptions() throws Exception {
        System.out.print("\nPlease select an option: ");
        switch (input.next().toUpperCase()){
            case "A": addStorm();
                break;
            case "L": lookStorm();
                break;
            case "D": removeStorm();
                break;
            case "E": editStorm();
                break;
            case "R": printByRain();
                break;
            case "W": printByWind();
                break;
            case "X": saveAndQuit();
                break;
            case "Q": Quit();
                break;
            default:
                System.out.println("Incorrect Menu Option. Try Again");
                break;
        }
    }

    private static void addStorm() {
        try{
            Storm.addStorm();
            writeToFile();
        }catch (Exception e){
            System.out.println("Couldn't add storm, try again");
        }
    }

    private static void lookStorm() {
        try{
            Storm.lookStorm();
        }catch (Exception e){
            System.out.println("Couldn't look up storm, try again");
        }
    }

    private static void removeStorm() {
        try{
            Storm.removeStorm();
        }catch (Exception e){
            System.out.println("Couldn't remove storm, try again");
        }
    }

    private static void editStorm() {
        try{
            Storm.editStorm();
        }catch (Exception e){
            System.out.println("Unable to edit storm, try again");
        }
    }
    private static void printByRain() {
        Set<String> keySet = database.keySet();
        ArrayList<Storm> stormList = new ArrayList<Storm>();
        for (String name: database.keySet()) {
            stormList.add(database.get(name));
        }
        System.out.println("Name    Windspeed   Rainfall    Date");
        System.out.println("------------------------------------");
        for(int i = 0; i< stormList.size(); i++){
            PrecipitationComparator precipitationComparator = new PrecipitationComparator();
            for (int j = i + 1; j < stormList.size(); j++){
                if(precipitationComparator.compare(stormList.get(i), stormList.get(j))==1){
                    Storm temp = stormList.get(i);
                    stormList.set(i, stormList.get(j));
                    stormList.set(j, temp);
                }
            }
        }
        for (int k = 0; k < stormList.size(); k++) {
            System.out.println(stormList.get(k));
        }
    }

    private static void printByWind() {
        Set<String> keySet = database.keySet();
        ArrayList<Storm> stormList = new ArrayList<Storm>();
        for (String name: database.keySet()) {
            stormList.add(database.get(name));
        }
        System.out.println("Name    Windspeed   Rainfall    Date");
        System.out.println("------------------------------------");
        for(int i = 0; i< stormList.size(); i++){
            WindSpeedComparator windSpeedComparator = new WindSpeedComparator();
            for (int j = i + 1; j < stormList.size(); j++){
                if(windSpeedComparator.compare(stormList.get(i), stormList.get(j))==1){
                    Storm temp = stormList.get(i);
                    stormList.set(i, stormList.get(j));
                    stormList.set(j, temp);
                }
            }
        }
        for (int k = 0; k < stormList.size(); k++) {
            System.out.println(stormList.get(k));
        }
    }

    private static void saveAndQuit() throws Exception {
        writeToFile();
        quitValue = true;
    }

    public static void writeToFile() throws Exception {
//        database.put("A",new Storm("A",2.3,23,"ASdf"));
        FileOutputStream fileOut = new FileOutputStream("hurricane.ser");
        ObjectOutputStream out = new ObjectOutputStream(fileOut);
        out.writeObject(database);
        out.close();
        fileOut.close();
    }

    private static void readFile() throws IOException, ClassNotFoundException {
        FileInputStream fileInput = new FileInputStream("hurricane.ser");
        ObjectInputStream in = new ObjectInputStream(fileInput);
        database = (HashMap<String,Storm>) in.readObject();
        in.close();
        fileInput.close();
    }

    private static void Quit() {
        System.out.println("Goodbye, it's hard to hold an (electric) candle in the cold November (and April!) rain!.");
        quitValue = true;
    }
}
