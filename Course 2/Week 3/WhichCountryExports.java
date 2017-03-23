
/**
 * Write a description of Part1 here.
 * 
 * @author Kenneth Umali 
 * @version 2.2.2017
 */
import edu.duke.*;
import org.apache.commons.csv.*;
public class WhichCountryExports {
    
    public void tester() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        
        /*//test countryInfo method
        System.out.println(countryInfo(parser, "Germany"));
        System.out.println(countryInfo(parser, "United States"));
        System.out.println(countryInfo(parser, "Philippines"));
        
        //test listExportersTwoProducts method
        parser = fr.getCSVParser();
        listExportersTwoProducts(parser, "gold", "diamonds");
        
        //test numberOfExporters method
        parser = fr.getCSVParser();
        System.out.println(numberOfExporters(parser, "gold"));
        
        //test bigExporters method
        parser = fr.getCSVParser();
        bigExporters(parser, "$999,999,999");*/
        
        //test listExportersTwoProducts method using exportdata.csv
        //listExportersTwoProducts(parser, "cotton", "flowers");
        
        //test numberOfExporters using exportdata.csv
        //parser = fr.getCSVParser();
        System.out.println(numberOfExporters(parser, "cocoa"));
        
        //test countryInfo using exportdata.csv
        //parser = fr.getCSVParser();
        //System.out.println(countryInfo(parser, "Nauru"));
        
        //test bigExporters using exportdata.csv
        //parser = fr.getCSVParser();
        //bigExporters(parser, "$999,999,999,999");
    }
    
    public String countryInfo(CSVParser parser, String country) {
        for(CSVRecord record : parser) {
            String countries = record.get("Country");
            if(countries.contains(country)) {
                String exports = record.get("Exports");
                String value = record.get("Value (dollars)");
                
                return country + ": " + exports + ": " + value;
            }
            
        }
        return "NOT FOUND";
    }
    
    public void listExportersTwoProducts(CSVParser parser, String exportItem1, String exportItem2) {
        for(CSVRecord record : parser) {
            String export = record.get("Exports");
            if(export.contains(exportItem1) && export.contains(exportItem2)) {
                String country = record.get("Country");
                System.out.println(country);
            }
        }
    }
    
    public int numberOfExporters(CSVParser parser, String exportItem) {
        int count = 0;
        for(CSVRecord record : parser) {
            String export = record.get("Exports");
            if(export.contains(exportItem)) {
                count++;
            }
        }
        return count;
    }
    
    public void bigExporters(CSVParser parser, String amount) {
        for(CSVRecord record : parser) {
            String value = record.get("Value (dollars)");
            if(value.length() > amount.length()) {
                String country = record.get("Country");
                System.out.println(country + " " + value);
            }
        }
    }
}
