
/**
 * Write a description of Part3 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part3 {
    
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
    
    public void printAllGenes(String dna) {
        int startIndex = 0;
        while(true) {
            String gene = findGene(dna, startIndex);
            if(gene.isEmpty()) {
                break;
            }
            System.out.println(gene);
            startIndex = dna.indexOf(gene, startIndex) + gene.length();
        }
    }
    
    public int countGenes(String dna) {
        int count = 0;
        int startIndex = 0;
        while(true) {
            String gene = findGene(dna, startIndex);
            if(gene.isEmpty()) {
                break;
            }
            int num = 4;
            System.out.println(num % 2 == num % 2);
            count++;
            startIndex = dna.indexOf(gene, startIndex) + gene.length();
        }
        return count;
    }
    
    public void testCountGenes() {
        String dna = "ATGTAAGATGCCCTAGT";
        System.out.println("DNA is: " + dna);
        System.out.println("Genes found: " + countGenes(dna));
        printAllGenes(dna);
    }
    
    public void test() {
        printAllGenes("AATGGCTTGAAATGATTTAGATGTCGTAAATGTGG"); //ATGGCTTGA, ATGATTTAG, ATGTCGTAA
    }
    
    public void testFindStopCodon() {
        String dna = "ATGTCGTGA"; //TGA index = 6
        System.out.println(findStopCodon(dna, 0, "TGA"));
        dna = "ATGTCGTGTAA"; //invalid. should return dna length = 11
        System.out.println(findStopCodon(dna, 0, "TAA"));
        dna = "ATGTCGTGCTAA"; //TAA index = 9
        System.out.println(findStopCodon(dna, 0, "TAA"));
    }
    
    public void testFindGene() {
        String dna = "ATGTCGTGA"; //ATGTCGTGA
        System.out.println(findGene(dna, 0));
    }
}
