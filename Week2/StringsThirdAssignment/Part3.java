
/**
 * Write a description of Part33 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;

public class Part33 {
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
    
    public String findGene(String dna, int where) {
        int startIndex = dna.indexOf("ATG", where);
        if (startIndex == -1) return "";
        int taaIndex = findStopCodon(dna, startIndex, "TAA");
        int tagIndex = findStopCodon(dna, startIndex, "TAG");
        int tgaIndex = findStopCodon(dna, startIndex, "TGA");
        int temp = Math.min(taaIndex, tagIndex);
        int minIndex = Math.min(temp, tgaIndex);
        if (minIndex == dna.length()) return "";
        return dna.substring(startIndex, minIndex + 3);
    }
    
    public StorageResource getAllGenes(String dna) {
        StorageResource geneList = new StorageResource();
        int currIndex = 0;
        while (true) {
            String gene = findGene(dna, currIndex);
            if (gene.isEmpty()) break;
            geneList.add(gene);
            currIndex = dna.indexOf(gene, currIndex) + gene.length();
        }
        return geneList;
    }
    
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

    public void processGenes(StorageResource sr) {
        int geneLenthCount_9 = 0;
        System.out.println("Strings in geneList that are longer than 9 characters: ");
        for (String g : sr.data()) {
            if (g.length() > 9) {
                System.out.println("Gene: " + g);
                geneLenthCount_9++;
            }
        }
        System.out.println("Number of strings in geneList that are longer than 9 characters: " + geneLenthCount_9);
        
        int geneLenthCount_60 = 0;
        System.out.println("Strings in geneList that are longer than 60 characters: ");
        for (String g : sr.data()) {
            if (g.length() > 60) {
                System.out.println("Gene: " + g);
                geneLenthCount_60++;
            }
        }
        System.out.println("Number of strings in geneList that are longer than 60 characters: " + geneLenthCount_60);
        
        int cgRatioCount = 0;
        System.out.println("Strings in sr whose C-G-ratio is higher than 0.35: ");
        for (String g : sr.data()) {
            if (cgRatio(g) > 0.35) {
                System.out.println("Gene: " + g);
                cgRatioCount++;
            }
        }
        System.out.println("Number of strings in sr whose C-G-ratio is higher than 0.35: " + cgRatioCount);
        
        String temp = "";
        System.out.println("Longest gene in sr: ");
        for (String g : sr.data()) {
            if (g.length() > temp.length()) {
                temp = g;
            }
        }
        System.out.println(temp);
    }
    
    public void testProcessGenes() {
        URLResource ur = new URLResource("https://www.dukelearntoprogram.com//course2/data/dna/GRch38dnapart.fa");
        String dna = ur.asString();
        
        StorageResource geneList = getAllGenes(dna);
        processGenes(geneList);
        
    }
}
