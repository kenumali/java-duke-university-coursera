
/**
 * Write a description of Part2 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
public class Part2 {

    public int findStopCodon(String dna, int startCodon, String stopCodon) {
        int currIndex = dna.indexOf(stopCodon, startCodon + 3);
        while(currIndex != -1) {
            int diff = currIndex - startCodon;
            if(diff % 3 == 0) {
                return currIndex;
            } else {
                currIndex = dna.indexOf(stopCodon, currIndex + 1);
            }
        }
        return dna.length();
    }
    
    public String findGene(String dna, int where) {
        int startIndex = dna.indexOf("ATG", where);
        if(startIndex == -1) {
            return "";
        }
        int taaIndex = findStopCodon(dna, startIndex, "TAA");
        int tagIndex = findStopCodon(dna, startIndex, "TAG");
        int tgaIndex = findStopCodon(dna, startIndex, "TGA");
        int minIndex = Math.min(taaIndex, Math.min(tagIndex, tgaIndex));
        if(minIndex == dna.length()) {
            return "";
        }
        return dna.substring(startIndex, minIndex + 3);
    }
    
    public StorageResource getAllGenes(String dna) {
        int startIndex = 0;
        StorageResource geneList = new StorageResource();
        
        while(true) {
            String currentGene = findGene(dna, startIndex);
            if(currentGene.isEmpty()) {
                break;
            }
            geneList.add(currentGene);
            startIndex = dna.indexOf(currentGene, startIndex + 1) + currentGene.length();
        }
        return geneList;
    }
    
    public float cgRatio(String dna) {
        int countC = 0;
        int countG = 0;
        int cIndex = dna.indexOf("C");
        int gIndex = dna.indexOf("G");
        while(true) {
            if(cIndex != -1) {
                countC++;
            } else {
                break;
            }
            if(gIndex != -1) {
                countG++;
            } else {
                break;
            }
            cIndex = dna.indexOf("C", cIndex+1);
            gIndex = dna.indexOf("G", gIndex+1);
        }
        float ratio = (float)(countC + countG) / dna.length();
        return ratio;
    }
    
    public int countCTG(String dna) {
        int startIndex = dna.indexOf("CTG");
        int count = 0;
        while(startIndex != -1) {
            count++;
            startIndex = dna.indexOf("CTG", startIndex + 1);
        }
        return count;
    }
    
    public void testcgRatio() {
        String dna = "ATGCCATAG"; //should return 4
        System.out.println(cgRatio(dna));
    }
    
    public void testCountCTG() {
        String dna = "CTGCTGCTGATGCTG"; //should return 4
        System.out.println(countCTG(dna));
    }
    
    public void testOn(String dna) {
        System.out.println("Testing getAllGenes on " + dna);
        StorageResource genes = getAllGenes(dna);
        for (String g: genes.data()) {
            System.out.println(g);
        }
    }
    public void test() {
        //      ATGv  TAAv  ATG   v  v  TGA   
        testOn("ATGATCTAATTTATGCTGCAACGGTGAAGA");
        testOn("");
        //      ATGv  v  v  v  TAAv  v  v  ATGTAA
        testOn("ATGATCATAAGAAGATAATAGAGGGCCATGTAA");
    }
}
