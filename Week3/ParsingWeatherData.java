/**
 * Write a description of ParsingWeatherData here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;

public class ParsingWeatherData {
    public CSVRecord coldestHourInFile(CSVParser parser) {
        CSVRecord smallestSoFar = null;
        for (CSVRecord currentRow : parser) {
            smallestSoFar = getSmallestOfTwo(currentRow, smallestSoFar);
        }
        return smallestSoFar;
    }

    public void testColdestHourInFile() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        CSVRecord smallest = coldestHourInFile(parser);
        System.out.println("Coldest Temperature was " + smallest.get("TemperatureF") + " at " + smallest.get("DateUTC"));
    }

    public String fileWithColdestTemperature() {
        CSVRecord smallestSoFar = null;
        File file = null;
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            CSVRecord currentRow = coldestHourInFile(fr.getCSVParser());

            if (smallestSoFar == null) {
                smallestSoFar = currentRow;
                file = f;
            } else {
                double currentTemp = Double.parseDouble(currentRow.get("TemperatureF"));
                double smallestTemp = Double.parseDouble(smallestSoFar.get("TemperatureF"));
                if (currentTemp < smallestTemp) {
                    smallestSoFar = currentRow;
                    file = f;
                }
            }
        }
        return file.getName();
    }

    public void testFileWithColdestTemperature() {
        String fileWithColdestTemperature = fileWithColdestTemperature();
        File f = new File(fileWithColdestTemperature);
        
        System.out.println("Coldest day was in file " + fileWithColdestTemperature);
        
        FileResource fr = new FileResource(f);
        CSVParser parser = fr.getCSVParser();
        CSVRecord smallest = coldestHourInFile(parser);
        System.out.println("Coldest temperature on that day was " + smallest.get("TemperatureF"));

        System.out.println("All the Temperatures on the coldest day were: ");
        parser = fr.getCSVParser();
        for (CSVRecord record : parser) {
            System.out.println(record.get("DateUTC") + ": " + record.get("TemperatureF"));
        }
    }

    public CSVRecord getSmallestOfTwo(CSVRecord currentRow, CSVRecord smallestSoFar) {
        if (smallestSoFar == null) {
            smallestSoFar = currentRow;
        } else {
            double currentTemp = Double.parseDouble(currentRow.get("TemperatureF"));
            double smallestTemp = Double.parseDouble(smallestSoFar.get("TemperatureF"));
            if (currentTemp < smallestTemp) {
                smallestSoFar = currentRow;
            }
        }
        return smallestSoFar;
    }

    public CSVRecord lowestHumidityInFile(CSVParser parser) {
        CSVRecord smallestSoFar = null;

        for (CSVRecord currentRow : parser) {
            if (smallestSoFar == null) {
                smallestSoFar = currentRow;
            } else {
                if (!currentRow.get("Humidity").equals("N/A") 
                && !smallestSoFar.get("Humidity").equals("N/A")) {
                    int currentTemp = Integer.parseInt(currentRow.get("Humidity"));
                    int smallestTemp = Integer.parseInt(smallestSoFar.get("Humidity"));
                    if (currentTemp < smallestTemp) {
                        smallestSoFar = currentRow;
                    }
                }
            }
        }
        return smallestSoFar;
    }

    public void testLowestHumidityInFile() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        CSVRecord csv = lowestHumidityInFile(parser);
        System.out.println("Lowest Humidity was " + csv.get("Humidity") + " at " + csv.get("DateUTC"));
    }

    public CSVRecord lowestHumidityInManyFiles() {
        DirectoryResource dr = new DirectoryResource();
        CSVRecord smallestSoFar = null;
        File file = null;

        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            CSVRecord currentRow = lowestHumidityInFile(fr.getCSVParser());

            if (smallestSoFar == null) {
                smallestSoFar = currentRow;
                file = f;
            } else {
                int currentTemp = Integer.parseInt(currentRow.get("Humidity"));
                int smallestTemp = Integer.parseInt(smallestSoFar.get("Humidity"));
                if (currentTemp < smallestTemp) {
                    smallestSoFar = currentRow;
                    file = f;
                }
            }
        }
        return smallestSoFar;
    }

    public void testLowestHumidityInManyFiles() {
        CSVRecord csv = lowestHumidityInManyFiles();
        System.out.println("Lowest Humidity in files was " + csv.get("Humidity") + " at " + csv.get("DateUTC"));
    }

    public double averageTemperatureInFile(CSVParser parser) {
        double total = 0;
        int count = 0;
        for (CSVRecord record : parser) {
            total += Double.parseDouble(record.get("TemperatureF"));
            count++;
        }
        double aveTemp = total / count;
        return aveTemp;
    }

    public void  testAverageTemperatureInFile() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        double aveTemp = averageTemperatureInFile(parser);
        System.out.println("Average temperature in file is " + aveTemp);
    }

    public double averageTemperatureWithHighHumidityInFile(CSVParser parser, int value) {
        double total = 0;
        int count = 0;
        for (CSVRecord record : parser) {
            if(!record.get("Humidity").equals("N/A") && Integer.parseInt(record.get("Humidity")) >= value) {
                total += Double.parseDouble(record.get("TemperatureF"));
                count++;
            }
        }

        if (count != 0) {
            double aveTemp = total / count;
            return aveTemp;
        } else {
            return -1.0;
        }

    }

    public void testAverageTemperatureWithHighHumidityInFile() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        int value = 80;
        double aveTemp = averageTemperatureWithHighHumidityInFile(parser, value);
        if (aveTemp == -1.0) {
            System.out.println("No temperatures with that humidity");
        } else {
            System.out.println("Average temperature when high Humidity is " + aveTemp);
        }

    }
}
