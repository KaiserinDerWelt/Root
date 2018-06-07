/* 
Author: Alexandra Aguirre
Created: 6-5-18
Modified: 6-7-18

NOTES: 
// need to check command                                                            Done: 6/7/18
// need to check if a driver has had no trips                                       Done: 6/7/18                              
// need to check if a trip should be discarded                                      
// need to convert start & end times to ints                                        Done: 6/7/18
// need to calculate total time driven                                              Done: 6/7/18
// need to calculate average MPH                                                    Done: 6/7/18
// need to add drivers, average MPH, and total miles driven all to one data set
// need to generate report (sort the output from most miles to least)
// need to update read me
// need to make hashmaps global for easier access
*/

import java.io.*;
import java.util.*;

import javax.swing.plaf.basic.BasicComboBoxUI.ItemHandler;

public class TrackDrivingHistory
{
    // calculates how long the person was driving for
    // set to void for now to avoid issues with java
    public static double tripTime(int startH, int startM, int endH, int endM)
    {
        // total amount of time on the road
        int totalH = endH - startH;
        int totalM = endM - startM;
        double totalTime = 0;

        // need total hours to be in minutes to do the right conversion
        int hourToMin = totalH * 60;

        totalTime = (double)(hourToMin + totalM)/(double)60;

        return totalTime;
    }

    // calculate the average MPH driven
    // void for now to avoid conflicts with java
    public static int avgMPH(double miles, double time)
    {
        double average = miles/time;

        return (int)Math.round(average);
    }

    // print the results
    public static void printReport(HashMap<String, Double> driversTime, HashMap<String, Double> milesDriven, HashMap<String, Integer> driversMPH)
    {

        for(String key : driversTime.keySet())
        {
            if(driversTime.get(key) == 0)
                milesDriven.put(key, (double)0);
        }

        // all of the miles a driver has driven
        List<Double> values = new ArrayList<Double>(milesDriven.values());

        // sort the miles driven from largest to smallest
        Collections.sort(values);
        Collections.reverse(values);

        for(int i = 0; i < values.size(); i++)
        {

            for(String key: milesDriven.keySet())
            {
                if(milesDriven.get(key) == values.get(i))
                {
                    int miles = (int)Math.round(values.get(i));

                    if(values.get(i) == 0)
                        System.out.println(key + ": " + miles + " miles");
                    else
                        System.out.println(key + ": " + miles + " miles @ " + driversMPH.get(key));
                }
            }           
        }
    }

    public static void main(String [] args) throws IOException
    {
        File file = new File("input.txt");
        Scanner sc = new Scanner(file);

        // keep track of the drivers' average MPH and how many miles they have driven
        HashMap<String, Double> driversTime = new HashMap<>();
        HashMap<String, Double> milesDriven = new HashMap<>();
        HashMap<String, Integer> driversMPH = new HashMap<>();
    
        // while there is still text in the input file, compute the calculations
        while(sc.hasNextLine())
        {
            // checks the current command
            String word = sc.next();

            // command: Driver
            // add the new driver to the hashmaps
            if(word.compareTo("Driver") == 0)
            {
                String driver = sc.next();

                driversTime.put(driver, (double)0);
            }
            // comand: Trip
            else if(word.compareTo("Trip") == 0)
            {
                // grab the data from the current trip
                String driver = sc.next();
                String start = sc.next();
                String end = sc.next();
                String miles = sc.next();

                Double driverMiles = Double.parseDouble(miles);

                // conver the start time to integers
                int startHour = Integer.parseInt(start.substring(0,2));
                int startMin = Integer.parseInt(start.substring(3,5));

                // convert the end times to integers
                int endHour = Integer.parseInt(end.substring(0,2));
                int endMin = Integer.parseInt(end.substring(3,5));

                // calculate the trip time
                double time = tripTime(startHour, startMin, endHour, endMin);

                // check if the driver has taken a trip before
                if(driversTime.get(driver) != 0)
                {   
                    // total time the driver has driven so far
                    driversTime.put(driver, driversTime.get(driver) + time);
                    milesDriven.put(driver, milesDriven.get(driver) + driverMiles);
                }
                // set the time and miles for the first time
                else
                {
                    // total time the driver has driven so far
                    driversTime.put(driver, time);
                    milesDriven.put(driver, driverMiles);
                }

                int avg = avgMPH(milesDriven.get(driver), driversTime.get(driver));

                driversMPH.put(driver, avg);
            }
        }

        printReport(driversTime, milesDriven, driversMPH);
    }
}