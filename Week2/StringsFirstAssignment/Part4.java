/**
 * Write a description of Part4 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;
import java.io.*;

public class Part4 {
    public void findURL() {
        String key = "youtube.com";
        String doubleQuote = "\"";
        URLResource url = new URLResource("https://www.dukelearntoprogram.com//course2/data/manylinks.html");
        
        for (String word : url.words()) {
            String wordLowerCase = word.toLowerCase();
            int location = wordLowerCase.indexOf(key);
            if (location != -1) {
                int startIndex = wordLowerCase.lastIndexOf(doubleQuote, location);
                int stopIndex = wordLowerCase.indexOf(doubleQuote, location);
                String address = word.substring(startIndex + 1, stopIndex);
                System.out.println(address);
            }
        }
    }
}
