/* 
Author: Alexandra Aguirre
Created: 6-5-18
Modified: 6-12-18

Solution to Root Interview Problem
Located in README.md
*/

import java.io.*;
import java.util.*;

public class TrackDrivingHistory
{

    // keep track of the drivers' times, average MPH, and how many miles they have driven
    static HashMap<String, Double> driversTime = new HashMap<>();
    static HashMap<String, Integer> driversMPH = new HashMap<>();
    static HashMap<String, Double> milesDriven = new HashMap<>();

    // calculations for a trip
    public static void calculateTrip(String driver, String start, String end, Double driverMiles)
    {

        // conver the start time to integers
        int startHour = Integer.parseInt(start.substring(0,2));
        int startMin = Integer.parseInt(start.substring(3,5));

        // convert the end times to integers
        int endHour = Integer.parseInt(end.substring(0,2));
        int endMin = Integer.parseInt(end.substring(3,5));

        // calculate the trip time
        double time = tripTime(startHour, startMin, endHour, endMin);
        
        // get the average MPH for this trip
        int tripAvg = avgMPH(driverMiles, time);

        // trip should not be discarded, continue calculations
        if(tripAvg >= 5 && tripAvg <= 100) 
        {
            // make sure the driver exists
            if(driversTime.get(driver) != null)
            {
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
                    driversTime.put(driver, time);
                    milesDriven.put(driver, driverMiles);
                }

                // add the average for the driver
                int avg = avgMPH(milesDriven.get(driver), driversTime.get(driver));
                driversMPH.put(driver, avg);
            }
        }
    }

    // calculates how long the current trip was
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
    public static int avgMPH(double miles, double time)
    {
        return (int)Math.round(miles/time);
    }

    // print the results to a file
    public static void printReport() throws IOException
    {
        // create output file
        FileWriter writer = new FileWriter("output.txt", true);
        BufferedWriter bw = new BufferedWriter(writer);

        // any driver that has not taken a trip gets a default of 0 miles
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

        // print output from largest mile to smallest
        for(int i = 0; i < values.size(); i++)
        {
            for(String key: milesDriven.keySet())
            {
                // match the driver, miles driven, and average MPH to the key
                if(milesDriven.get(key) == values.get(i))
                {
                    // round the miles
                    int miles = (int)Math.round(values.get(i));

                    // get rid of extra spacline at the end of the output file
                    if(i == values.size() - 1)
                    {
                       // print the correct output
                        if(values.get(i) == 0)
                            bw.write(key + ": " + miles + " miles");
                        else
                            bw.write(key + ": " + miles + " miles @ " + driversMPH.get(key) + " mph");
                    }
                    else
                    {
                        // print the correct output
                        if(values.get(i) == 0)
                            bw.write(key + ": " + miles + " miles" + System.lineSeparator());
                        else
                            bw.write(key + ": " + miles + " miles @ " + driversMPH.get(key) + " mph" + System.lineSeparator());
                    }
                }
            }           
        }

        bw.close();
    }

/* --------------------------------------------------------------------------- */

    public static void main(String[] args ) throws IOException
    {
        if(args.length < 1)
        {
            System.out.println("Proper syntax: java TrackDrivingHistory <filename> ");
            return;
        }
        
        // make sure the output file is blank to begin with
        PrintWriter writer = new PrintWriter("output.txt");
        writer.print("");
        writer.close();
        
        // input file
        File file = new File(args[0]);
        Scanner sc = new Scanner(file);
    
        // while there is still text in the input file, compute the calculations
        while(sc.hasNextLine())
        {
            // checks the current command
            String word = sc.next().toLowerCase();

            // command: Driver
            // add the new driver to the hashmaps
            if(word.compareTo("driver") == 0)
            {
                String driver = sc.next();
                driversTime.put(driver, (double)0);
            }
            // command: Trip
            else if(word.compareTo("trip") == 0)
            {
                // grab the data from the current trip
                String driver = sc.next();
                String start = sc.next();
                String end = sc.next();
                String miles = sc.next();

                Double driverMiles = Double.parseDouble(miles);
                
                // calculations for current trip
                calculateTrip(driver, start, end, driverMiles);
            }
        }
     
        printReport();
    }
}