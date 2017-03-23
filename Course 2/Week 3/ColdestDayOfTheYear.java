
/**
 * Write a description of ColdestDayOfTheYear here.
 * 
 * @author Kenneth Umali 
 * @version 6.2.2017
 */

import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;
public class ColdestDayOfTheYear {

    public CSVRecord coldestHourInFile(CSVParser parser) {
        CSVRecord coldestSoFar = null;
        for(CSVRecord currentRow : parser) {
            if(coldestSoFar == null) {
                coldestSoFar = currentRow;
            } else {
                double currentTemp = Double.parseDouble(currentRow.get("TemperatureF"));
                double coldestTemp = Double.parseDouble(coldestSoFar.get("TemperatureF"));
                if(currentTemp < coldestTemp) {
                    if(currentTemp != -9999) {
                        coldestSoFar = currentRow;                        
                    }
                }
            }
        }
        return coldestSoFar;
    }
    
    public String fileWithColdestTemperature() {
        CSVRecord coldestSoFar = null;
        String filename = null;
        DirectoryResource dr = new DirectoryResource();
        for(File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            CSVRecord currentRow = coldestHourInFile(fr.getCSVParser());
            if(coldestSoFar == null) {
                coldestSoFar = currentRow;
                filename = f.getName();
            } else {
                double currentTemp = Double.parseDouble(currentRow.get("TemperatureF"));
                double coldestTemp = Double.parseDouble(coldestSoFar.get("TemperatureF"));
                if(currentTemp < coldestTemp) {
                    coldestSoFar = currentRow;
                    filename = f.getName();
                }
            }
            
        }
        return filename;
    }
    
    public CSVRecord lowestHumidityInFile(CSVParser parser) {
        CSVRecord lowestHumiditySoFar = null;
        for(CSVRecord currentRow : parser) {
            lowestHumiditySoFar = getLowestOfTwo(currentRow, lowestHumiditySoFar);
        }
        return lowestHumiditySoFar;
    }
    
    public CSVRecord lowestHumidityInManyFiles() {
        CSVRecord lowestSoFar = null;
        DirectoryResource dr = new DirectoryResource();
        for(File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            CSVRecord current = lowestHumidityInFile(fr.getCSVParser());
            lowestSoFar = getLowestOfTwo(current, lowestSoFar);
        }
        return lowestSoFar;
    }
    
    public CSVRecord getLowestOfTwo(CSVRecord currentRow, CSVRecord lowestHumiditySoFar) {
        if(lowestHumiditySoFar == null) {
            lowestHumiditySoFar = currentRow;
        } else {
            String currentHumidityStr = currentRow.get("Humidity");
            String humidityStr = lowestHumiditySoFar.get("Humidity");
            if(!currentHumidityStr.equalsIgnoreCase("N/A") && !humidityStr.equalsIgnoreCase("N/A")) {
                double currentHumidity = Double.parseDouble(currentHumidityStr);
                double humidity = Double.parseDouble(humidityStr);
                if(currentHumidity < humidity) {
                    lowestHumiditySoFar = currentRow;
                }
            }
       }
        return lowestHumiditySoFar;
    }
    
    public double averageTemperatureInFile(CSVParser parser) {
        double sum = 0.0;
        double average = 0.0;
        for(CSVRecord currentRow : parser) {
            double temp = Double.parseDouble(currentRow.get("TemperatureF"));
            sum += temp;
            average = sum / (parser.getCurrentLineNumber() - 1);
        }
        return average;
    }
    
    public double averageTemperatureWithHighHumidityInFile(CSVParser parser, int value) {
        double sum = 0.0;
        double average = 0.0;
        int counter = 0;
        for(CSVRecord currentRow : parser) {
            String humidityStr = currentRow.get("Humidity");
            if(!humidityStr.equalsIgnoreCase("N/A")) {
                double humidity = Double.parseDouble(humidityStr);
                
                if(humidity >= value) {
                    double temp = Double.parseDouble(currentRow.get("TemperatureF"));
                    
                    System.out.println(humidity + " " + temp);
                    sum += temp;
                    counter ++;
                    average = sum / counter;
                }
            }
         }
         return average;
    }
    
    public void testColdestHourInFile() {
        FileResource fr = new FileResource();
        CSVRecord coldest = coldestHourInFile(fr.getCSVParser());
        System.out.println("Coldest Temperature was: " + coldest.get("TemperatureF") +
                            " at " + coldest.get("TimeEDT"));
    }
    
    public void testFileWithColdestTemperature() {
        String filename = fileWithColdestTemperature();
        //CSVRecord coldest = 
        FileResource fr = new FileResource("nc_weather/2013/" + filename);
        CSVRecord coldest = coldestHourInFile(fr.getCSVParser());
        System.out.println("Coldest day was in file: " + filename);
        System.out.println("Coldest temperature on that day was " + coldest.get("TemperatureF"));
        System.out.println("All the Temperatures on the coldest day were: ");
        for(CSVRecord record : fr.getCSVParser()) {
            String date = record.get("DateUTC");
            String temp = record.get("TemperatureF");
            System.out.println(date + " " + temp);
        }
    }
    
    public void testLowestHumidityInFile() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        CSVRecord csv = lowestHumidityInFile(parser);
        System.out.println("Lowest Humidity was " + csv.get("Humidity") + " at " + csv.get("DateUTC"));
    }
    
    public void testLowestHumidityInManyFiles() {
        CSVRecord lowest = lowestHumidityInManyFiles();
        System.out.println("Lowest Humidity was " + lowest.get("Humidity") +
                            " at " + lowest.get("DateUTC"));
    }
    
    public void testAverageTemperatureInFile() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        double avg = averageTemperatureInFile(parser);
        System.out.println("Average temperature in file is " + avg);
    }
    
    public void testAverageTemperatureWithHighHumidityInFile() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        int value = 80;
        double avg = averageTemperatureWithHighHumidityInFile(parser, 80);
        if(avg <= 0.0) {
            System.out.println("No temperatures with that humidity");
        } else {
            System.out.println("Average Temp when high Humidity is " + avg);
        }
    }
}
