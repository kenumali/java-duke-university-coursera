
/**
 * Write a description of Part1 here.
 * 
 * @author Kenneth Umali
 * @version 25.1.2017
 */
public class Part1 {
    
    public String findSimpleGene(String dna) {
        int startCodon = dna.indexOf("ATG");
        if(startCodon == -1) {
            return "";
        }
        int stopCodon = dna.indexOf("TAA", startCodon + 3 );
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
        String gene = findSimpleGene(dna);
        System.out.println("Gene is " + gene);
        
        dna = "CGATGAAATGCTG"; //prints empty string has no TAA
        System.out.println("DNA strand is " + dna);
        gene = findSimpleGene(dna);
        System.out.println("Gene is " + gene);
        
        dna = "CGAAATGCTG"; //prints empty string has no ATG or TAA
        System.out.println("DNA strand is " + dna);
        gene = findSimpleGene(dna);
        System.out.println("Gene is " + gene);
        
        dna = "TGCATGTTTCTGTAATG"; //prints ATGTTTCTGTAA
        System.out.println("DNA strand is " + dna);
        gene = findSimpleGene(dna);
        System.out.println("Gene is " + gene);
        
        dna = "TGCATGTTCTGTAATG"; //prints empty string not multiple of 3
        System.out.println("DNA strand is " + dna);
        gene = findSimpleGene(dna);
        System.out.println("Gene is " + gene);
        
        dna = "AAATGCCCTAACTAGATTAAGAAACC"; //prints ATGCCCTAA
        System.out.println("DNA strand is " + dna);
        gene = findSimpleGene(dna);
        System.out.println("Gene is " + gene);
        
        dna = "AAATGCCCCTGTGCGGTTAACTAGATTAAGAAACC"; //prints ATGCCCCTGTGCGGTTAA
        System.out.println("DNA strand is " + dna);
        gene = findSimpleGene(dna);
        System.out.println("Gene is " + gene);
    }
}
