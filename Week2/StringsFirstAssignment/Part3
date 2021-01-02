/**
 * Write a description of Part3 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;
import java.io.*;

public class Part3 {
    public boolean twoOccurrences (String stringa, String stringb) {
        int count = 0;
        int index = 0;
        while (true) {
            index = stringb.indexOf(stringa, index + stringa.length());
            if (index == -1) break;
            count++;
        }
        
        if (count >= 2) {
            return true;
        } else {
            return false;
        }
    }
    
    public String lastPart(String stringa, String stringb) {
        int startPoint = stringb.indexOf(stringa);
        if (startPoint == -1) return stringb;
        String lastPart = stringb.substring(startPoint + stringa.length());
        return lastPart;
    }
    
    public void testMethods() {
        // Ture
        String stringa = "by";
        String stringb = "A story by Abby Long";
        if (twoOccurrences(stringa, stringb) == true) {
            System.out.println("True");
        } else {
            System.out.println("False");
        }
        
        // False
        stringa = "Abby";
        stringb = "A story by Abby Long";

        if (twoOccurrences(stringa, stringb) == true) {
            System.out.println("True");
        } else {
            System.out.println("False");
        }
        
        
        // Return ana
        stringa = "an";
        stringb = "banana";
        System.out.println(lastPart(stringa, stringb));
        
        // Return forest
        stringa = "zoo";
        stringb = "forest";
        System.out.println(lastPart(stringa, stringb));
    }
}
