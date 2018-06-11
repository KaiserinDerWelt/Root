# Root Interview Solution
To solve this problem, I went with Java and the sourcecode is `TrackDrivingHistory.java`. My thought process was to use three seperate HashMaps to contain all of the driver's information because they have an O(1) insertion time, O(1) lookup time, and O(n) to iterate through values. These three HashMaps are used to track how the total time a driver was on the road, the total miles a driver drove, and the average MPH the driver achieved.

First, the Java program will receive the input text file and a while loop will run, parsing each line, until there is no more lines of text in the input file. In this while loop, it will parse each word of the file. First, it will change first word in the line to lowercase (in case a user made a typo). This will check if the word is "Driver" by using a `.compareTo("driver") == 0`. If the word is "Driver", it will parse the next word and add that new driver to the HashMap for the time a driver was on the road, with an initial value of 0. This is under the assumption that no two (or more) drivers will have the same name. If the word is not "Driver", it will check if the (now lowercase) word is "Trip" by using a comparison as well, `.compareTo("trip") == 0`. When the word is "Trip", it will pare the next four words in order as the driver, the start time, the end time, and the miles driven. 

Once this data is parse, it will be sent to the `calculateTrip()` method. Here, the start time, end time, and miles will be parsed to their correct data types. Then, the start and end times are sent to another method, `tripTime()` that will calculate the total amount the driver drove for that trip. After this is returned, an if statement is used to see if a driver has taken a trip before or not. If the driver has taken a trip before, the driver's time is updated in the HashMap for driver's time and the amount of miles driven will be updated in the respective HashMap, as well. If not, the driver's time and miles will be added to their respective HashMaps for the first time. As soon as these are checked and done, a method called `avgMPH()` will calculate the average MPH the driver has achieved by getting the values from the HashMaps that were used to store the driver's time and miles. The lookup time for both of the HashMaps are O(1). This new average MPH time will be added to the third HashMap, the one used to track the driver's average MPH. This one does not need to check if there was data in that driver's key for the HashMap before as it can override the previous value because the `avgMPH()` method uses the latest total time and total miles driven for the driver.

When the while loop has finished, because there are no more lines in the text file, the `printReport()` method will be called to show the final results. These results are outputed to the terminal. Here, the first step if to check if any driver's time is equal to zero. If so, we will add a 0 for that driven in the total miles driven HashMap. Then, a new list is created to sort the values in the miles driven HashMap because the output is done from most miles driven to least. This is done with `Collections.sort()` and takes O(n * log(n)) time. The sorting will not be done until the method is called. Next, to have the values from most miles driven to least, I used `Collections.reverse()` because the soritng is done from smallest value to biggest. Finally, the output is printed through a double for loop with runtime O(n^2) (unfortunately). The first for loop is used to iterate through each value of the newly created list of miles driven, and the second for loop is used to iterate through every key in the miles driven HashMap. Then, the method checks if that key's value is equal to the value of the current index in the new list, and if so, it will print the result. If the current value is 0, because that person did not take a trip, the output will be `(Driver): 0 miles`. Otherwise, the output will be `(Driver): x miles @ y mph`. 

# Testing
Testing process...

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
