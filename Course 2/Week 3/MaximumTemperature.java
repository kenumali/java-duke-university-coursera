
/**
 * Write a description of MaximumTemperature here.
 * 
 * @author Kenneth Umali 
 * @version 6.2.2017
 */

import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;
public class MaximumTemperature {
    public CSVRecord hottestHourInFile(CSVParser parser) {
        //start with largestSoFar as nothing
        CSVRecord largestSoFar = null;
        //For each row (currentRow) in the CSV File
        for(CSVRecord currentRow : parser) {
            //If largestSoFar is nothing
            largestSoFar = getLargestOfTwo(currentRow, largestSoFar);
        }
        //largestSoFar is our answer
        return largestSoFar;
    }
    
    public CSVRecord hottestInManyDays() {
        CSVRecord largestSoFar = null;
        DirectoryResource dr = new DirectoryResource();
        for(File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            CSVRecord current = hottestHourInFile(fr.getCSVParser());
            largestSoFar = getLargestOfTwo(current, largestSoFar);
        }
        return largestSoFar;
    }
    
    public CSVRecord getLargestOfTwo(CSVRecord currentRow, CSVRecord largestSoFar) {
        if(largestSoFar == null) {
            largestSoFar = currentRow;
        } else {
            double currentTemp = Double.parseDouble(currentRow.get("TemperatureF"));
            double largestTemp = Double.parseDouble(largestSoFar.get("TemperatureF"));
            if(currentTemp > largestTemp) {
                largestSoFar = currentRow;
            }
        }
        return largestSoFar;
    }
    
    public void testHottestInDay() {
        FileResource fr = new FileResource("data/2015/weather-2015-01-02.csv");
        CSVRecord largest = hottestHourInFile(fr.getCSVParser());
        System.out.println("Hottest Temperature was: " + largest.get("TemperatureF") +
                            " at " + largest.get("TimeEST"));
    }
    
    public void testHottestInManyDays() {
        CSVRecord largest = hottestInManyDays();
        System.out.println("Hottest Temperature was: " + largest.get("TemperatureF") +
                            " at " + largest.get("DateUTC"));
    }
}
