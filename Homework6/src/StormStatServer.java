//Jimmy Zhang CSE 214 R02 ID: 112844431

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Set;

/**
 * this class represents the driver class for the storm
 */
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
    /**
     * the function below represents the main method
     */
    public static void main(String[] args) throws Exception {
        System.out.println("\nWelcome to the StormStatServer, we may not be able to make it rain, but we sure can tell you when it happened!\n");
        File file = new File("hurricane.ser");
        if(file.isFile()){
            readFile();
            System.out.println("hurricane.ser was found and loaded.\n");
        }else{
            System.out.println("No previous data found\n");
        }

        displayMenu();
        while(!quitValue){
            menuOptions();
        }
    }
    /**
     * the function below displays the menu when it is called
     */
    public static void displayMenu(){
        System.out.println("Menu:\n" +
                "    A) Add A Storm\n" +
                "    L) Look Up A Storm\n" +
                "    D) Delete A Storm\n" +
                "    E) Edit Storm Data\n" +
                "    R) Print Storms Sorted By Rainfall\n" +
                "    W) W-Print Storms by Windspeed\n" +
                "    X) Save and quit\n" +
                "    Q) Quit and delete saved data ");
    }
    /**
     *the function below asks for user inputs and properly executes a list of functions
     */
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
    /**
     * the function below adds a storm to the data base
     */
    private static void addStorm() {
        try{
            Storm.addStorm();
        }catch (Exception e){
            System.out.println("Couldn't add storm, try again");
        }
    }
    /**
     * the function below looks up a storm to the data base
     */
    private static void lookStorm() {
        try{
            Storm.lookStorm();
        }catch (Exception e){
            System.out.println("Couldn't look up storm, try again");
        }
    }
    /**
     * the function below removes a storm to the data base
     */
    private static void removeStorm() {
        try{
            Storm.removeStorm();
        }catch (Exception e){
            System.out.println("Couldn't remove storm, try again");
        }
    }
    /**
     * the function below edits a storm to the data base
     */
    private static void editStorm() {
        try{
            Storm.editStorm();
        }catch (Exception e){
            System.out.println("Unable to edit storm, try again");
        }
    }
    /**
     * the function below prints the storm in the data base by the value of precipitation
     */
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
    /**
     * the function below prints the storm in the data base by the value of windspeed
     */
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
    /**
     * the function below saves and quits the function
     */
    private static void saveAndQuit() throws Exception {
        writeToFile();
        System.out.println("File saved to hurricane.ser; feel free to use the weather channel in the meantime. ");
        quitValue = true;
    }

    /**
     * the function below writes the database to a serializable file
     */
    public static void writeToFile() throws Exception {
//        database.put("A",new Storm("A",2.3,23,"ASdf"));
        FileOutputStream fileOut = new FileOutputStream("hurricane.ser");
        ObjectOutputStream out = new ObjectOutputStream(fileOut);
        out.writeObject(database);
        out.close();
        fileOut.close();
    }
    /**
     * the function below reads a file and deserializes it
     */
    private static void readFile() throws IOException, ClassNotFoundException {
        FileInputStream fileInput = new FileInputStream("hurricane.ser");
        ObjectInputStream in = new ObjectInputStream(fileInput);
        database = (HashMap<String,Storm>) in.readObject();
        in.close();
        fileInput.close();
    }

    /**
     * the function below quits the function
     */
    private static void Quit() {
        System.out.println("Goodbye, it's hard to hold an (electric) candle in the cold November (and April!) rain!.");
        quitValue = true;
        File myObj = new File("hurricane.ser");
        myObj.delete();
    }
}
