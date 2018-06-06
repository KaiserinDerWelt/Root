/* 
Author: Alexandra Aguirre
Created: 6-5-18
Modified: 

*/
import java.io.*;
import java.util.*;

public class TrackDrivingHistory
{
    public static void main(String [] args) throws Exception
    {
        File file = new File("input.txt");
        Scanner sc = new Scanner(file);
        HashMap<String, String> namesStart = new HashMap<>();
        HashMap<String, String> namesEnd = new HashMap<>();

       // ArrayList drivers = new ArrayList<>();

        while(sc.hasNextLine())
        {
            if(sc.next().compareTo("Driver") == 0)
            {
                String driver = sc.next();
                namesStart.put(driver, "start");
                namesEnd.put(driver, "end");
            }

            if(sc.next().compareTo("Trip") == 0)
            {
                String driver = sc.next();
            }

            
          //  System.out.println(sc.next());

        }

        System.out.println(namesStart);
    }
}