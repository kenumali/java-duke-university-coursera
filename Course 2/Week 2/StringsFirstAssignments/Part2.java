
/**
 * Write a description of Part2 here.
 * 
 * @author Kenneth Umali
 * @version 25.1.2017
 */
public class Part2 {
    public String findSimpleGene(String dna, int startCodon, int stopCodon) {
        for (int i = 0; i < dna.length(); i++) {
            if(Character.isUpperCase(dna.charAt(i))) {
                startCodon = dna.indexOf("ATG");
                stopCodon = dna.indexOf("TAA", startCodon + 3 );
            } else {
                startCodon = dna.indexOf("atg");
                stopCodon = dna.indexOf("taa", startCodon + 3 );
            }
        }
        if(startCodon == -1) {
            return "";
        }
        if(stopCodon == -1) {
            return "";
        }
        if((stopCodon - startCodon) % 3 == 0) {
            return dna.substring(startCodon, stopCodon + 3);
        }
        return "";
    }
    
    public void testSimpleGene() {
        String dna = "CGTGATTCTAATG"; //prints empty string has no ATG
        System.out.println("DNA strand is " + dna);
        String gene = findSimpleGene(dna, 0, 12);
        System.out.println("Gene is " + gene);
        
        dna = "CGATGAAATGCTG"; //prints empty string has no TAA
        System.out.println("DNA strand is " + dna);
        gene = findSimpleGene(dna, 2, 5);
        System.out.println("Gene is " + gene);
        
        dna = "CGAAATGCTG"; //prints empty string has no ATG or TAA
        System.out.println("DNA strand is " + dna);
        gene = findSimpleGene(dna, 5, 7);
        System.out.println("Gene is " + gene);
        
        dna = "TGCATGTTTCTGTAATG"; //prints ATGTTTCTGTAA
        System.out.println("DNA strand is " + dna);
        gene = findSimpleGene(dna, 0, 15);
        System.out.println("Gene is " + gene);
        
        dna = "gatgctataat"; //prints atgctataa
        System.out.println("DNA strand is " + dna);
        gene = findSimpleGene(dna, 0, 15);
        System.out.println("Gene is " + gene);
        
        dna = "TGCATGTTCTGTAATG"; //prints empty string not multiple of 3
        System.out.println("DNA strand is " + dna);
        gene = findSimpleGene(dna, 0, 10);
        System.out.println("Gene is " + gene);
    }
}
