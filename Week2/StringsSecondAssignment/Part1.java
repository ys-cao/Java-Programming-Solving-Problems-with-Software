
/**
 * Write a description of Part1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;
import java.io.*;

public class Part1 {
    public int findStopCodon (String dna, int startIndex, String stopCodon) {
        /** returns the index of the first occurrence of stopCodon that appears 
         * past startIndex and is a multiple of 3 away from startIndex. If there is 
         * no such stopCodon, this method returns the length of the dna strand.
         */
        int currIndex = dna.indexOf(stopCodon, startIndex + 3);
        while (currIndex != -1) {
            if ((currIndex - startIndex) % 3 == 0) {
                return currIndex;
            } else {
                currIndex = dna.indexOf(stopCodon, currIndex + 1);
            }
        }
        return dna.length();
    }
    
    public void testFindStopCodon() {
        // calls the method findStopCodon with several examples and prints out 
        // the results to check if findStopCodon is working correctly.
        String dna = "xxxyyyzzzTAAxxxyyyzzzTAAxx";
        int dex = findStopCodon(dna, 0, "TAA");
        System.out.println("tests finihsed");
    }
    
    public String findGene(String dna) {
        int startIndex = dna.indexOf("ATG");
        if (startIndex == -1) return "";
        int taaIndex = findStopCodon(dna, startIndex, "TAA");
        int tagIndex = findStopCodon(dna, startIndex, "TAG");
        int tgaIndex = findStopCodon(dna, startIndex, "TGA");
        int temp = Math.min(taaIndex, tagIndex);
        int minIndex = Math.min(temp, tgaIndex);
        if (minIndex == dna.length()) return "";
        return dna.substring(startIndex, minIndex + 3);
    }
    
    public void testFindGene() {
        String dna = "ATGyyzzzTAAxxxxyyyzzzTAAxx";
        String gene = findGene(dna);
        System.out.println(gene);
    }
    
    public void printAllGenes(String dna) {
        System.out.println(findGene(dna));
    }
}
