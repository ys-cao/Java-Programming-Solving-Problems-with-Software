
/**
 * Write a description of BabyNames here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;
import java.lang.*;

public class BabyNames {
    public void totalBirths() {
        FileResource fr = new FileResource();
        int totalBirths = 0;
        int girlsBirths = 0;
        int boysBirths = 0;
        for (CSVRecord record : fr.getCSVParser(false)) {
            int numBorn = Integer.parseInt(record.get(2));
            if (record.get(1).equals("F")) {
                girlsBirths += numBorn;
            } else {
                boysBirths += numBorn;
            }
            totalBirths += numBorn;
        }
        System.out.println("Total Births: " + totalBirths);
        System.out.println("Total girls: " + girlsBirths);
        System.out.println("Total boys: " + boysBirths);
    }
    
    public int getRank(int year, String name, String gender) {
        String fileName = "us_babynames/us_babynames_by_year/yob" + year + ".csv";
        FileResource fr = new FileResource(fileName);
        int rank = 0;
        boolean found = false;
        for (CSVRecord record : fr.getCSVParser(false)) {
            if (record.get(1).equals(gender)) {
                rank++;
                if (record.get(0).equals(name)) {
                    found = true;
                    break;
                }
            }
        }
        if (found) {
            return rank;
        } else {
            return -1;
        }
    }
    
    public String getName(int year, int rank, String gender) {
        String fileName = "us_babynames/us_babynames_by_year/yob" + year + ".csv";
        FileResource fr = new FileResource(fileName);
        int currRank = 0;
        boolean found = false;
        String name = "";
        for (CSVRecord record : fr.getCSVParser(false)) {
            if (record.get(1).equals(gender)) {
                currRank++;
                if (currRank == rank) {
                    found = true;
                    name = record.get(0);
                    break;
                } 
            }
        }
        if (found) {
            return name;
        } else {
            return "This year doesn't have this rank";
        }
        
    }
    
    public void whatIsNameInYear(String name, int year, int newYear, String gender) {
        int rank = getRank(year, name, gender);
        String newName = getName(newYear, rank, gender);
        String sex = "";
        if (gender.equals("F")) {
            sex = "she";
        } else {
            sex = "he";
        }
        System.out.println(name + " born in " + year + " would be " + newName + " if " + sex + " was born in " + newYear + ".");
    }
    
    public int yearOfHighestRank(String name, String gender) {
        DirectoryResource dr = new DirectoryResource();
        int temp = Integer.MAX_VALUE;
        int yearofHighestRank = -1;
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            String fileName = f.getName();
            int yearEndIndex = fileName.lastIndexOf(".csv");
            String sYear = fileName.substring(yearEndIndex - 4, yearEndIndex);
            int year = Integer.parseInt(sYear);
            int rank = getRank(year, name, gender);
            if (rank != -1 && rank < temp) {
                temp = rank;
                yearofHighestRank = year;
            }
        }
        return yearofHighestRank;
    }
    
    public double getAverageRank(String name, String gender) {
        DirectoryResource dr = new DirectoryResource();
        double totalRank = 0;
        int count = 0;
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            String fileName = f.getName();
            int yearEndIndex = fileName.lastIndexOf(".csv");
            String sYear = fileName.substring(yearEndIndex - 4, yearEndIndex);
            int year = Integer.parseInt(sYear);
            int rank = getRank(year, name, gender);
            if (rank != -1) {
                totalRank += rank;
                count++;
            }
        }
        if (totalRank == 0) {
            return -1;
        } else {
            return (totalRank / count);
        }
            
    }
    
    public int getTotalBirthsRankedHigher(int year, String name, String gender) {
        String fileName = "us_babynames/us_babynames_by_year/yob" + year + ".csv";
        FileResource fr = new FileResource(fileName);
        int totalBirthsRankedHigher = 0;
        boolean found = false;
        for (CSVRecord record : fr.getCSVParser(false)) {
            if (record.get(1).equals(gender)) {
                if (!record.get(0).equals(name)) {
                    totalBirthsRankedHigher += Integer.parseInt(record.get(2));
                } else {
                    found = true;
                    break;
                }
            }
        }
        if (found) {
            return totalBirthsRankedHigher;
        } else {
            return -1;
        }
    }
}
