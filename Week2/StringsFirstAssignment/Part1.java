
/**
 * Write a description of Part1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;
import java.io.*;

public class Part1 {
    public String findSimpleGene(String dna) {
        int startIndex = dna.indexOf("ATG");
        int stopIndex = dna.indexOf("TAA", startIndex + 3);
        
        String result = "";
        
        if (startIndex == -1 || stopIndex == -1) return result;
        if ((stopIndex - startIndex) % 3 == 0) {
            result = dna.substring(startIndex, stopIndex + 3);
        }
        return result;
    }
    
    public void testSimpleGene() {
        // DNA without ATG
        String dna = "CGTAGCAATTATAA";
        System.out.println("DNA strand is " + dna);
        System.out.println("Gene is " + findSimpleGene(dna));
        
        // DNA without TAA
        dna = "CATGGCAATTATTA";
        System.out.println("DNA strand is " + dna);
        System.out.println("Gene is " + findSimpleGene(dna));
        
        // DNA without ATG and TAA
        dna = "CGTAGCAATTATCA";
        System.out.println("DNA strand is " + dna);
        System.out.println("Gene is " + findSimpleGene(dna));
        
        // DNA with ATG and TAA but substring is not a multiple of 3.
        dna = "CGATGCAATATAA";
        System.out.println("DNA strand is " + dna);
        System.out.println("Gene is " + findSimpleGene(dna));
        
        // DNA with ATG and TAA but substring iS a multiple of 3.
        dna = "CGATGCAATTATAA";
        System.out.println("DNA strand is " + dna);
        System.out.println("Gene is " + findSimpleGene(dna));
    }
}
