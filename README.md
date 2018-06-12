# Set Up
To use this code, you must have Java JDK 7+ installed. If you do not have Java, you may obtain it by following the instructions at https://java.com/en/download/help/download_options.xml . You do not need NetBeans or Eclipsed installed to run this, all commands and compilation can be done through the terminal.

# Usage
Once you have ensured that Java is installed in your machine, 
1. Download or git clone the repository onto your computer
2. Through the terminal, access the source file with `cd Root/src/`
3. Compile the code with `javac TrackDrivingHistory.java`
4. Run it with the file with `java TrackDrivingHistory input.txt`

The output will appear in a textfile titled `output.txt`

To test the code, please following the instructions in the `Testing` outline.

# Root Interview Solution Implementation
To solve this problem, I went with Java and the sourcecode is `TrackDrivingHistory.java`. My thought process was to use three seperate HashMaps to contain all of the driver's information because they have an O(1) insertion time, O(1) lookup time, and O(n) to iterate through values. These three HashMaps are used to track how the total time a driver was on the road, the total miles a driver drove, and the average MPH the driver achieved.

You will notice in the git history that each commit was to add a new step to the code or include test cases. The code was done step by step, breaking the problem into smaller, easier problems and working my way back up from there. In each commit, the source file has a `NOTES:` section that shows what was completed and when. Also, the code has comments explaining more of the mechanisms of each method.

# Testing
The testing process was done through the use of a bash script. There are 7 test cases, each for a different test:
1. Tests if the code catches more than 1 driver who has not taken a trip
2. Tests if the code discards any trips that average less than 5 MPH or more than 100 MPH
3. Tests if the code catches any typos with the commands (`Driver` and `Trip`)
4. Tests if the code catches two drivers who average the same miles and MPH in their total trips.
5. Tests if the code catches and discards any drivers who take a trip but were not inputted as a driver.
6. Tests if the code catches displays the correct output when there are only drivers and no trips.
7. Tests if the code catches when there are drivers are a trip.

To run the bash script, make sure you are in the `/src/` file in the terminal and type `bash test-all.sh`. 

This script works by compiling the `TrackDrivingHistory.java` file and using the given tests files (ex: `Test01.txt`) as the input, and comparing the `output.txt` file that is created with the solution test file (ex: `TestSol01.txt`). If the test cases passes, the output will be `PASS`, but if it fails the output will be `fail (output does not match)`. 

# Assumptions
There are a few assumptions I have made with this code that were not specified in the problem statement:
1. A new driver may be added after someone else's trip has been recorded
2. No two drivers will have the same first name
3. If a trip is given with a driver who has not been recorded with the driver command yet, discard it

# Problem Statement
Let's write some code to track driving history for people.

The code will process an input file. You can either choose to accept the input via stdin (e.g. if you're using Ruby `cat input.txt | ruby yourcode.rb)`, or as a file name given on the command line (e.g. `ruby yourcode.rb input.txt)`. You can use any programming language that you want. Please choose a language that allows you to best demonstrate your programming ability.

Each line in the input file will start with a command. There are two possible commands.

The first command is Driver, which will register a new Driver in the app. Example:

`Driver Dan`

The second command is Trip, which will record a trip attributed to a driver. The line will be space delimited with the following fields: the command (Trip), driver name, start time, stop time, miles driven. Times will be given in the format of hours:minutes. We'll use a 24-hour clock and will assume that drivers never drive past midnight (the start time will always be before the end time). Example:

`Trip Dan 07:15 07:45 17.3`

Discard any trips that average a speed of less than 5 mph or greater than 100 mph.

Generate a report containing each driver with total miles driven and average speed. Sort the output by most miles driven to least. Round miles and miles per hour to the nearest integer.

Example input:

```
Driver Dan
Driver Alex
Driver Bob
Trip Dan 07:15 07:45 17.3
Trip Dan 06:12 06:32 21.8
Trip Alex 12:01 13:16 42.0
```

Expected output:
```
Alex: 42 miles @ 34 mph
Dan: 39 miles @ 47 mph
Bob: 0 miles`
```
