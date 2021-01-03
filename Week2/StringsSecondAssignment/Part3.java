
/**
 * Write a description of Part3 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part3 {
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

    public void printAllGenes(String dna) {
        int currIndex = 0;
        while (true) {
            String gene = findGene(dna, currIndex);
            if (gene.isEmpty()) break;
            System.out.println("Gene strand is " + gene);
            currIndex = dna.indexOf(gene, currIndex) + gene.length();
        }
    }
    
    public int countGene(String dna) {
        int count = 0;
        int currIndex = 0;
        while (true) {
            String gene = findGene(dna, currIndex);
            if (gene.isEmpty()) break;
            currIndex = dna.indexOf(gene, currIndex) + gene.length();
            count++;
        }
        return count;
    }
    
    public void testCountGenes() {
        String dna = "ATGTAAGATGCCCTAGT";
        printAllGenes(dna);
        System.out.println("Gene Count: " + countGene(dna));
    }
}
