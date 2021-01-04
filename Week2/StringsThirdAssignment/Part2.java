
/**
 * Write a description of Part2 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;

public class Part2 {
    public double cgRatio(String dna) {
        double count = 0.0;
        for (int i = 0; i < dna.length(); i++) {
            if (dna.toUpperCase().charAt(i) == 'C' ||
                dna.toUpperCase().charAt(i) == 'G') {
                    count++;
                }
        }
        double ratio = count / dna.length();
        return ratio;
    }
    
    public int countCTG(String dna) {
        int count = 0;
        int startIndex = 0;
        while (true) {
            int currIndex = dna.indexOf("CTG", startIndex);
            if (currIndex == -1) break;
            count++;
            startIndex = currIndex + 3;
        }
        return count;
    }
}
