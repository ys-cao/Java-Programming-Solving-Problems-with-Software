
/**
 * Write a description of Part2 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */


import edu.duke.*;
import java.io.*;

public class Part2 {
    public int howMany(String stringa, String stringb) {
        int count = 0;
        int startIndex = 0;
        while (true) {
            int currIndex = stringb.indexOf(stringa, startIndex);
            if (currIndex == -1) break;
            count++;
            startIndex = currIndex + stringa.length();
        }
        return count;
    }
    
    public void testHowMany() {
        System.out.println("How Many: " + howMany("GAA", "ATGAACGAATTGAATC"));
        System.out.println("How Many: " + howMany("AA", "ATAAAA"));
    }
}
