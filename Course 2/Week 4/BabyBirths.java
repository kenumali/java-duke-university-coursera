
/**
 * Write a description of BabyBirths here.
 * 
 * @author Kenneth Umali 
 * @version 7.2.2017
 */

import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;
public class BabyBirths {
        
    public void totalBirths(FileResource fr) {
        int totalBirths = 0;
        int totalBoys = 0;
        int totalGirls = 0;
        int girlsNames = 0;
        int boysNames = 0;
        for(CSVRecord record : fr.getCSVParser(false)) {
            int numBorn = Integer.parseInt(record.get(2));
            String name = record.get(0);
            totalBirths += numBorn;
            if(record.get(1).equalsIgnoreCase("M")) {
                totalBoys += numBorn;
                boysNames = countNames(name, boysNames);
            } else {
                totalGirls += numBorn;
                girlsNames = countNames(name, girlsNames);
            }
        }
        System.out.println("Total Births = " + totalBirths);
        System.out.println("Total Girls = " + totalGirls);
        System.out.println("Total Boys = " + totalBoys);
        System.out.println("Number of Girls Names = " + girlsNames);
        System.out.println("Number of Boys Names = " + boysNames);
        System.out.println("Total Names = " + (boysNames + girlsNames));
    }
    
    public int countNames(String name, int count) {
        String temp = null;
        String temp1 = null;
        if(temp == null) {
            temp = name;
        }
        if(temp1 != temp) {
            temp1 = temp;
            count++;
        }
        return count;
    }
    
    //should return the rank of the name in the file for the given gender
    public int getRank(int year, String name, String gender) {
        String filename = "data/yob" + year + ".csv";
        FileResource fr = new FileResource(filename);
        int rank = 1;
        int boysNames = 0;
        for(CSVRecord record : fr.getCSVParser(false)) {
            String nameRow = record.get(0);
            String genderRow = record.get(1);
            if(genderRow.equalsIgnoreCase("M")) {
                boysNames = countNames(name, boysNames);
                rank = boysNames;
            }
            if(nameRow.equalsIgnoreCase(name) && genderRow.equalsIgnoreCase(gender)) {
                return rank;
            }
            rank++;
        }
        return -1;
    }
    
    //should return the name of the person in the file at this rank for the given gender
    public String getName(int year, int rank, String gender) {
        String filename = "data/yob" + year + ".csv";
        FileResource fr = new FileResource(filename);
        CSVParser parser = fr.getCSVParser(false);
        String name = null;
        int girlsNames = 0;
        int maleRank = rank;
        int lineNumber = 0;
        for(CSVRecord record : parser) {
            String genderRow = record.get(1);
            lineNumber++;
            if(genderRow.equalsIgnoreCase("F")) {
                girlsNames = countNames(record.get(0), girlsNames);
            }
            
            if(genderRow.equalsIgnoreCase("M") && gender.equalsIgnoreCase("M")) {
                rank = maleRank + girlsNames;
            }
            if(genderRow.equals(gender) && rank == lineNumber) {
                return name = record.get(0);
            }
        }
        return "NO NAME";
    }
    
    public void whatIsNameInYear(String name, int year, int newYear, String gender) {
        int rank = getRank(year, name, gender);
        String newName = getName(newYear, rank, gender);
        if(gender.equalsIgnoreCase("M")) {
            System.out.println(name + " born in " + year + " would be " + newName + 
                            " if he was born in " + newYear);
        } else {
            System.out.println(name + " born in " + year + " would be " + newName + 
                            " if she was born in " + newYear);
        }
    }
    
    public int yearOfHighestRank(String name, String gender) {
        int year = -1;
        int tempYear = 0;
        int previousRank = 0;
        DirectoryResource dr = new DirectoryResource();
        for(File f : dr.selectedFiles()) {
            String filename = f.getName();
            String getYear = filename.substring(filename.indexOf("yob") + 3, filename.indexOf(".csv"));
            tempYear = Integer.parseInt(getYear);
            int currentRank = getRank(tempYear, name, gender);
            if((currentRank != -1) && (previousRank == 0 || currentRank < previousRank)) {
                previousRank = currentRank;
                year = tempYear;
            }
        }
        return year;
    }
    
    public double getAverageRank(String name, String gender) {
        double average = -1.0;
        int sum = 0;
        int count = 0;
        DirectoryResource dr = new DirectoryResource();
        for(File f : dr.selectedFiles()) {
            String filename = f.getName();
            String getYear = filename.substring(filename.indexOf("yob") + 3, filename.indexOf(".csv"));
            int year = Integer.parseInt(getYear);
            int rank = getRank(year, name, gender);
            sum += rank;
            count++;
            average = (double)sum / count;
        }
        return average;
    }
    
    public int getTotalBirthsRankedHigher(int year, String name, String gender) {
        String filename = "data/yob" + year + ".csv";
        FileResource fr = new FileResource(filename);
        CSVParser parser = fr.getCSVParser(false);
        int totalBirths = 0;
        int count = 0;
        int boysCount = 0;
        int rank = getRank(year, name, gender);
        for(CSVRecord record : parser) {
            int numBorn = Integer.parseInt(record.get(2));
            if(record.get(1).equals("M") && gender.equals("M")) {
                boysCount++;
                if(boysCount < rank) {
                    totalBirths += numBorn;
                }
            } else if(record.get(1).equals("F") && gender.equals("F")){
                count++;
                if (count < rank) {
                    totalBirths += numBorn;
                }
            }
        }
        return totalBirths;
    }
    
    public void testTotalBirths() {
        FileResource fr = new FileResource();
        totalBirths(fr);
    }
    
    public void testGetRank() {
        /*System.out.println(getRank(2012, "Emma", "F")); //2
        System.out.println(getRank(2012, "Sophia", "F")); //1
        System.out.println(getRank(2012, "Ava", "F")); //5
        System.out.println(getRank(2012, "Olivia", "F")); //4
        System.out.println(getRank(2012, "Isabella", "F")); //3
        
        System.out.println(getRank(2012, "Mason", "M")); //2 because male
        System.out.println(getRank(2012, "William", "M")); // 5
        System.out.println(getRank(2012, "Jacob", "M")); // 1
        System.out.println(getRank(2012, "Noah", "M")); // 4
        System.out.println(getRank(2012, "Ethan", "M")); // 3
        
        System.out.println(getRank(2012, "Mason", "F")); // -1 because Mason is M
        System.out.println(getRank(2012, "Ava", "M")); //-1 because Ava is F
        System.out.println(getRank(2012, "Ken", "M")); //-1 because there is no Ken
        */
       System.out.println(getRank(2010, "Alyssa", "F")); //20
       System.out.println(getRank(2010, "Ladarius", "F")); //-1
       System.out.println(getRank(2010, "Ladarius", "M")); //21556(position of Ladarius) - 19800(last position of F) = 1756
       System.out.println(getRank(2010, "Jacob", "M")); //1
       System.out.println(getRank(2010, "Brayden", "M")); //40
       System.out.println(getRank(2010, "Jowel", "M")); //7834
       System.out.println(getRank(2012, "Noah", "F")); //7834
       
       System.out.println("Emily 1960: " + getRank(1960, "Emily", "F"));
       System.out.println("Frank 1971: " + getRank(1971, "Frank", "M"));
    }
    
    public void testGetName() {
        /*System.out.println(getName(2012, 2, "F")); //Emma
        System.out.println(getName(2012, 5, "F")); //Ava
        System.out.println(getName(2012, 6, "F")); //NO NAME because 6 is M
        System.out.println(getName(2012, 9, "M")); //Noah
        System.out.println(getName(2012, 7, "M")); //Mason
        System.out.println(getName(2012, 1, "M")); //Jacon*/
        
        System.out.println(getName(2010, 12, "F")); //Elizabeth
        System.out.println(getName(2010, 1000, "F")); //Elizabeth
        System.out.println(getName(2010, 10, "M")); //Anthony
        System.out.println(getName(2010, 1, "M")); //Jacob
        System.out.println(getName(2010, 500, "M")); //Jamison
        System.out.println(getName(2010, 100, "M")); //Bentley
        System.out.println(getName(2010, 100, "F")); //Paige
        
        System.out.println(getName(1980, 350, "F")); //Mia
        System.out.println(getName(1982, 450, "M")); //Forrest
    }
    
    public void testWhatIsNameInYear() {
        whatIsNameInYear("Isabella", 2012, 2014, "F"); //Sophia
        whatIsNameInYear("Noah", 2014, 2012, "M"); //Jacob
        whatIsNameInYear("Noah", 2014, 2012, "F"); // Brianne
        
        whatIsNameInYear("Hello", 1972, 2014, "F"); // Addison
        whatIsNameInYear("Owen", 1974, 2014, "M"); // Leonel
    }
    
    public void testYearOfHighestRank() {
        //System.out.println(yearOfHighestRank("Mason", "M")); //2012 ranks 2nd
        //System.out.println(yearOfHighestRank("Alyssa", "F")); //2012 ranks 44th
        //System.out.println(yearOfHighestRank("Izza", "F")); // 2014 ranks 10382nd
    
        //System.out.println(yearOfHighestRank("Mich", "M")); // 1880
        System.out.println(yearOfHighestRank("Genevieve", "F")); // 1914
    }
    
    public void testGetAverageRank() {
        //System.out.println(getAverageRank("Mason", "M")); //3.0
        //System.out.println(getAverageRank("Jacob", "M")); //2.66
        
        //System.out.println(getAverageRank("Susan", "F")); //173.51
        System.out.println(getAverageRank("Robert", "M")); //10.75
    }
    
    public void testGetTotalBirthsRankedHigher() {
        //System.out.println(getTotalBirthsRankedHigher(2013, "Olivia", "F")); //42023
        
        System.out.println(getTotalBirthsRankedHigher(1990, "Emily", "F")); //323200
        System.out.println(getTotalBirthsRankedHigher(1990, "Drew", "M")); //1134053
        //System.out.println(getTotalBirthsRankedHigher(2012, "Ava", "F"));
    }
}
